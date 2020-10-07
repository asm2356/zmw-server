package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.model.Article;
import com.iflytek.common.model.Discuss;
import com.iflytek.common.model.ReplyDiscuss;
import com.iflytek.common.model.dto.UserArticleDiscuss;
import com.iflytek.common.model.vo.UserComment;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.config.service.redis.RedisService;
import com.iflytek.manager.api.DiscussService;
import com.iflytek.manager.dao.DiscussDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Service
public class DiscussServiceImp implements DiscussService {
    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private RedisService redisService;

    @Override
    public int getDiscussArticleCount(String articleId) {
        return discussDao.getDiscussArticleCount(articleId);
    }

    @Override
    public List<Discuss> getDiscussList(String articleId, long startNum, int pageSize) {
        List<Discuss> discussList = discussDao.getDiscussList(articleId, startNum, pageSize);
        if (discussList == null)
            return null;
        for (int i = 0; i < discussList.size(); i++) {
            List<ReplyDiscuss> replyDiscusses = discussList.get(i).getReplyDiscussList();
            for (int j = 0; j < replyDiscusses.size(); j++) {
                ReplyDiscuss replyDiscuss = replyDiscusses.get(j);
                if (replyDiscuss.getReplyDiscussId() == null || replyDiscuss.getReplyDiscussId().equals("")) {
                    discussList.get(i).getReplyDiscussList().remove(j);
                }
            }
        }
        return discussList;
    }

    @Override
    public List<Discuss> getOnlyDiscussList(Map<String, Object> map) {
        return discussDao.getOnlyDiscussList(map);
    }

    @Override
    public int getOnlyDiscussCount(Map<String, Object> map) {
        return discussDao.getOnlyDiscussCount(map);
    }

    @Override
    public void increaseDiscussCount(String articleId) {
        if (redisService.hasKey(RedisPrefix.DISCUSS_NUMBER + articleId)) {
            redisService.incr(RedisPrefix.DISCUSS_NUMBER + articleId, 1);
        } else {
            redisService.set(RedisPrefix.DISCUSS_NUMBER + articleId, getDiscussArticleCount(articleId));
        }
    }

    @Override
    public void decreaseDiscussCount(String articleId) {
        if (redisService.hasKey(RedisPrefix.DISCUSS_NUMBER + articleId)) {
            redisService.decr(RedisPrefix.DISCUSS_NUMBER + articleId, 1);
        } else {
            redisService.set(RedisPrefix.DISCUSS_NUMBER + articleId, getDiscussArticleCount(articleId));
        }
    }


    @Override
    public String writeDiscuss(Discuss discuss) {
        discuss.setDiscussId(RandomUtils.getUUID());
        return discussDao.insertDiscuss(discuss) > 0 ? discuss.getDiscussId() : null;
    }

    @Override
    public String writeReplyDiscuss(ReplyDiscuss replyDiscuss) {
        replyDiscuss.setReplyDiscussId(RandomUtils.getUUID());
        return discussDao.insertReplyDiscuss(replyDiscuss) > 0 ? replyDiscuss.getReplyDiscussId() : null;
    }

    @Override
    public int deleteDiscuss(String discussId) {
        return discussDao.deleteDiscuss(discussId);
    }

    @Override
    public int deleteReplyDiscuss(String replyDiscussId) {
        return discussDao.deleteReplyDiscuss(replyDiscussId);
    }

    @Override
    public int updateDiscussPraise(final String discussId, final int praiseNumber) {
        return discussDao.updateDiscussPraise(discussId, praiseNumber);
    }

    @Override
    public int updateReplyDiscussPraise(final String replyDiscussId, final int praiseNumber) {
        return discussDao.updateReplyDiscussPraise(replyDiscussId, praiseNumber);
    }

    @Override
    public List<UserComment> getUserCommentList(final String mwId, final int startNum, final int pageSize) {
        List<UserArticleDiscuss> list = discussDao.getUserArticleDiscussList(mwId, startNum, pageSize);
        List<UserComment> userCommentList = new ArrayList<>();
        for (UserArticleDiscuss item : list) {
            Discuss discuss = item.getDiscuss();
            ReplyDiscuss replyDiscuss = item.getReplyDiscuss();
            Article article = item.getArticle();

            if (discuss.getUserBaseInfo() != null && discuss.getUserBaseInfo().getMwId().equals(mwId)) {
                UserComment userComment = new UserComment();
                userComment.setId(discuss.getDiscussId());
                userComment.setArticle(article);
                userComment.setContent(discuss.getContent());
                userComment.setPraiseNumber(discuss.getPraiseNumber());
                userComment.setDiscussTime(discuss.getDiscussTime());
                if (!userCommentList.contains(userComment)) {
                    userCommentList.add(userComment);
                }
            }
            if (replyDiscuss.getUserBaseInfo() != null && replyDiscuss.getUserBaseInfo().getMwId().equals(mwId)) {
                UserComment userComment = new UserComment();
                userComment.setId(replyDiscuss.getReplyDiscussId());
                userComment.setArticle(article);
                userComment.setDiscussTime(replyDiscuss.getDiscussTime());
                userComment.setPraiseNumber(replyDiscuss.getReplyPraiseNumber());
                userComment.setContent(replyDiscuss.getContent());
                userComment.setTargetUserBaseInfo(replyDiscuss.getTargetUserBaseInfo());
                userComment.setUserBaseInfo(replyDiscuss.getUserBaseInfo());
                userComment.setToContent(replyDiscuss.getToContent());
                userCommentList.add(userComment);
            }
        }
        return userCommentList;
    }

    @Override
    public int getUserDiscussNumber(String mwId) {
        Map<String, Object> discussMap = new HashMap<>();
        discussMap.put("mwId", mwId);
        int discussCount = getOnlyDiscussCount(discussMap);
        Map<String, Object> replyMap = new HashMap<>();
        discussMap.put("mwId", mwId);
        int replyCount = getReplyDiscussCount(replyMap);
        return discussCount + replyCount;
    }

    @Override
    public int deleteDiscussByArticleId(String articleId) {
        discussDao.deleteReplyDiscussArticleById(articleId);
        return discussDao.deleteDiscussByArticleId(articleId);
    }

    @Override
    public List<ReplyDiscuss> getReplyDiscussList(Map<String, Object> map) {
        return discussDao.getReplyDiscussList(map);
    }

    @Override
    public int getReplyDiscussCount(Map<String, Object> map) {
        return discussDao.getReplyDiscussCount(map);
    }
}
