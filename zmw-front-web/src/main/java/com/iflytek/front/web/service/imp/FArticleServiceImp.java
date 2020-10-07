package com.iflytek.front.web.service.imp;

import com.iflytek.common.constant.ConfigConstant;
import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.common.model.Article;
import com.iflytek.common.model.Result;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.config.service.redis.RedisService;
import com.iflytek.front.web.service.FArticleService;
import com.iflytek.manager.api.ArticleService;
import com.iflytek.manager.api.UserBaseInfoService;
import org.apache.activemq.ScheduledMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.ObjectMessage;
import java.util.Date;
/**
 * @author AZhao
 */
@Service
public class FArticleServiceImp implements FArticleService{
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserBaseInfoService userBaseInfoService;

    private static final Logger logger = LoggerFactory.getLogger(FArticleService.class);

    public Result uploadArticle(Article article) {
        long currentTime = System.currentTimeMillis();
        final long timeDifference = article.getReleaseTime() - currentTime;
        if (timeDifference < 0) {
            article.setReleaseTime(new Date().getTime());
        }
        Subject subject = SecurityUtils.getSubject();
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId(subject.getPrincipal().toString());
        userBaseInfo = userBaseInfoService.getUserBaseInfoById(userBaseInfo.getMwId());
        article.setUserBaseInfo(userBaseInfo);
        jmsTemplate.send(ConfigConstant.ARTICLE_RELEASE_QUEUE, session -> {
            ObjectMessage message = session.createObjectMessage(article);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, timeDifference);
            return message;
        });
        return new Result().success();
    }

    public Result<Article> getArticleByPwd(String articleId, String pwd, String key, String code) {
        Result<Article> result = new Result<>();
        if (!redisService.hasKey(key)) {
            logger.info("验证码过期");
            return result.failure(ResultCode.Verification_Code_Expire);
        }
        String value = (String) redisService.get(key);
        logger.info(value);
        if (!value.toLowerCase().equals(code.toLowerCase())) {
            logger.info("验证码错误");
            return new Result<>(ResultCode.Verification_Code_Error);
        }
        Article article;
        //缓存加密的文章，时间20s
        if (redisService.hasKey(RedisPrefix.ARTICLE_ID + articleId)) {
            article = (Article) redisService.get(RedisPrefix.ARTICLE_ID + articleId);
        } else {
            article = articleService.getArticle(articleId);
        }
        String encryptPwd = new Md5Hash(pwd, ByteSource.Util.bytes(article.getArticleId()).toString()).toString();
        if (article.getPwd().equals(encryptPwd)) {
            //更新文章阅读量
            articleService.updateReadingNumber(articleId);
            return result.success(article);
        } else {
            return result.failure(ResultCode.Article_Pwd_Error);
        }
    }

    public Result<Article> getArticle(String articleId) {
        Result<Article> result = new Result<>();
        Article article = articleService.getArticle(articleId);
        if (article == null) {
            return result.failure(ResultCode.Article_Not_Exist);
        }
        //如果是自己的文章 //无密进入
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() != null && subject.getPrincipal().toString().equals(article.getUserBaseInfo().getMwId())) {
            return result.success(article);
        }
        if (article.getOpenness() == Openness.Not_Open.getValue()) {
            return result.failure(ResultCode.Article_Not_Open);
        } else if (article.getOpenness() == Openness.Encrypt_Open.getValue()) {
            redisService.set(RedisPrefix.ARTICLE_ID + articleId, article, 20);
            return result.failure(ResultCode.Article_Encrypt_Open);
        }
        articleService.updateReadingNumber(articleId);
        return result.success(article);
    }
}
