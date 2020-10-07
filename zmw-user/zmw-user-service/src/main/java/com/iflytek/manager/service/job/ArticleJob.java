package com.iflytek.manager.service.job;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.config.service.redis.RedisService;
import com.iflytek.manager.api.ArticleService;
import com.iflytek.manager.api.DiscussService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author AZhao
 */
@JobHandler(value = "articleJob")
@Component
public class ArticleJob extends IJobHandler {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private DiscussService discussService;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        //每隔一段时间将redis里面的文章数据存到数据库当中
        //阅读量
        updateReadingNumber();
        // 文章 点赞量
        updateArticlePraiseNumber();
        //评论 点赞量
        updateDiscussPraiseNumber();
        //回复评论 点赞量
        updateReplyPraiseNumber();
        return new ReturnT<>("success");
    }

    private void updateArticlePraiseNumber() {
        Set<String> praiseNumberKeys = redisService.keys(RedisPrefix.ARTICLE_PRAISE_NUMBER + "*");
        for (String key : praiseNumberKeys) {
            int praiseNumber = Integer.parseInt(String.valueOf(redisService.get(key)));
            redisService.del(key);
            String articleId = key.substring(RedisPrefix.ARTICLE_PRAISE_NUMBER.length());
            articleService.updatePraise(articleId, praiseNumber);
        }
    }

    private void updateDiscussPraiseNumber() {
        Set<String> praiseNumberKeys = redisService.keys(RedisPrefix.DISCUSS_PRAISE_NUMBER + "*");
        for (String key : praiseNumberKeys) {
            int praiseNumber = Integer.parseInt(String.valueOf(redisService.get(key)));
            redisService.del(key);
            String articleId = key.substring(RedisPrefix.DISCUSS_PRAISE_NUMBER.length());
            discussService.updateDiscussPraise(articleId, praiseNumber);
        }
    }

    private void updateReplyPraiseNumber() {
        Set<String> praiseNumberKeys = redisService.keys(RedisPrefix.REPLY_PRAISE_NUMBER + "*");
        for (String key : praiseNumberKeys) {
            int praiseNumber = Integer.parseInt(String.valueOf(redisService.get(key)));
            redisService.del(key);
            String articleId = key.substring(RedisPrefix.REPLY_PRAISE_NUMBER.length());
            discussService.updateReplyDiscussPraise(articleId, praiseNumber);
        }
    }


    private void updateReadingNumber() {
        Set<String> readingNumberKeys = redisService.keys(RedisPrefix.ARTICLE_READING_NUMBER + "*");
        for (String key : readingNumberKeys) {
            int readingNumber = Integer.parseInt(String.valueOf(redisService.get(key)));
            redisService.del(key);
            //不做更新只做redis 保存到数据库当中
            String articleId = key.substring(RedisPrefix.ARTICLE_READING_NUMBER.length());
            articleService.updateReadingNumber(articleId, readingNumber);
        }
    }


}
