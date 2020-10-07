package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.enumeration.user.ArticleStatus;
import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.common.exception.DeleteFileException;
import com.iflytek.common.exception.PageException;
import com.iflytek.common.model.Article;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.config.service.other.FastDFSClient;
import com.iflytek.config.service.redis.RedisService;
import com.iflytek.manager.api.ArticleService;
import com.iflytek.manager.api.CollectService;
import com.iflytek.manager.api.DiscussService;
import com.iflytek.manager.api.UserBaseInfoService;
import com.iflytek.manager.dao.ArticleDao;
import com.iflytek.search.api.SearchArticleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Service
public class ArticleServiceImp implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private FastDFSClient fastDFSClient;
    @Autowired
    private CollectService collectService;

    @Autowired
    private DiscussService discussService;

    @Autowired
    private RedisService redisService;
    @Reference
    private SearchArticleService searchArticleService;
    @Override
    public List<Article> getArticleList(Map<String, Object> map) throws PageException {
        if (!map.containsKey("startNum")) {
            throw new PageException(ResultCode.Start_Num_Is_Null.getCode(), ResultCode.Start_Num_Is_Null.getMessage());
        }
        if (!map.containsKey("pageSize")) {
            throw new PageException(ResultCode.Page_Size_Is_Null.getCode(), ResultCode.Page_Size_Is_Null.getMessage());
        }
        if (map.containsKey("openness")) {
            int value = (int) map.get("openness");
            if (value == Openness.ALL.getValue()) {
                map.remove("openness");
            }
        }
        if (!map.containsKey("status")) {
            map.put("status", ArticleStatus.HAS_RELEASE.getValue());
        }
        if (!map.containsKey("hot")) {
            map.put("hot", false);
        }
        return articleDao.getArticleList(map);
    }

    @Override
    public int getArticleNumber(Map<String, Object> map) {
        if (map.containsKey("openness")) {
            int value = (int) map.get("openness");
            if (value == Openness.ALL.getValue()) {
                map.remove("openness");
            }
        }
        if (!map.containsKey("status")) {
            map.put("status", ArticleStatus.HAS_RELEASE.getValue());
        }
        return articleDao.getArticleNumber(map);
    }

    @Override
    public Article getArticle(String articleId) {
        Article article = articleDao.getArticle(articleId);
        //缓存赞
        if (redisService.hasKey(RedisPrefix.ARTICLE_PRAISE_NUMBER + articleId)) {
            int num = Integer.parseInt(String.valueOf(redisService.get(RedisPrefix.ARTICLE_PRAISE_NUMBER + articleId)));
            int currentNum = article.getPraiseNumber();
            article.setPraiseNumber(currentNum + num);
        }
        return article;
    }

    @Override
    public Article writeArticle(Article article) {
        article.setArticleId(RandomUtils.getUUID());
        String pwdTemp = article.getPwd();
        if (!StringUtils.isBlank(pwdTemp)) {
            String encryptPwd = new Md5Hash(pwdTemp, ByteSource.Util.bytes(article.getArticleId()).toString()).toString();
            article.setPwd(encryptPwd);
        }
        articleDao.insertArticle(article);

        searchArticleService.insertArticle(article);
        return article;
    }


    private void updatePraise(String id, boolean isIncrement, String type) {
        String key = type + id;
        if (!redisService.hasKey(key)) {
            redisService.set(key, 1);
        } else {
            if (isIncrement) {
                redisService.incr(key, 1);
            } else {
                redisService.decr(key, 1);
            }
        }
    }

    @Override
    public void updateArticlePraise(String articleId, boolean isIncrement) {
        updatePraise(articleId, isIncrement, RedisPrefix.ARTICLE_PRAISE_NUMBER);
    }

    @Override
    public void updateDiscussPraise(String discussId, boolean isIncrement) {
        updatePraise(discussId, isIncrement, RedisPrefix.DISCUSS_PRAISE_NUMBER);
    }

    @Override
    public void updateReplyPraise(String replyId, boolean isIncrement) {
        updatePraise(replyId, isIncrement, RedisPrefix.REPLY_PRAISE_NUMBER);
    }

    @Override
    public void updatePraise(String articleId, int increment) {
        Map<String,Object> map = new HashMap<>();
        map.put("praiseNumber",searchArticleService.getArticle(articleId).getReadingNumber()+increment);
        searchArticleService.updateArticle(articleId,map);
        articleDao.updatePraise(articleId, increment);
    }

    @Override
    public void updateReadingNumber(String articleId) {
        if (!redisService.hasKey(RedisPrefix.ARTICLE_READING_NUMBER + articleId)) {
            redisService.set(RedisPrefix.ARTICLE_READING_NUMBER + articleId, 1);
        } else {
            redisService.incr(RedisPrefix.ARTICLE_READING_NUMBER + articleId, 1);
        }
    }

    @Override
    public void updateReadingNumber(String articleId, int increment) {
        Map<String,Object> map = new HashMap<>();
        map.put("readingNumber",searchArticleService.getArticle(articleId).getReadingNumber()+increment);
        searchArticleService.updateArticle(articleId,map);
        articleDao.updateReadingNumber(articleId, increment);
    }

    @Override
    public int getReadingNumber(String mwId) {
        return articleDao.getReadingNumber(mwId);
    }

    private void deleteFile(String content) throws DeleteFileException {
        List<String> urlList = new ArrayList<>();
        Document doc = Jsoup.parse(content);
        Elements imgElements = doc.select("img[src]");
        Elements videoElements = doc.select("video[src]");
        for (Element element : imgElements) {
            urlList.add(element.attr("src"));
        }
        for (Element element : videoElements) {
            urlList.add(element.attr("src"));
        }
        for (String url : urlList) {
            if (url != null && !url.equals("")) {
                fastDFSClient.deleteFile(fastDFSClient.getDFSFile(url));
            }
        }
    }

    @Override
    public int deleteArticle(String articleId) throws DeleteFileException {
        discussService.deleteDiscussByArticleId(articleId);
        collectService.deleteCollectByArticleId(articleId);
        Article article = getArticle(articleId);
        deleteFile(article.getContent());
        if (article.getMusic() != null) {
            fastDFSClient.deleteFile(fastDFSClient.getDFSFile(article.getMusic()));
        }
        searchArticleService.deleteArticle(articleId);
        return articleDao.deleteById(articleId);
    }


    @Override
    public void updateOpenness(final String articleId, final int openness, final String pwd) {
        String encryptPwd = new Md5Hash(pwd, ByteSource.Util.bytes(articleId).toString()).toString();
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 3661289469376723199L;
            {
                put("articleId", articleId);
                put("openness", openness);
                put("pwd", encryptPwd);
            }
        };
        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("openness",openness);
        searchMap.put("pwd",encryptPwd);
        searchArticleService.updateArticle(articleId,searchMap);
        articleDao.updateOpenness(map);
    }

}
