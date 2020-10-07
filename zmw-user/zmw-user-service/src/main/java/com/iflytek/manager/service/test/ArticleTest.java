package com.iflytek.manager.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.enumeration.user.ArticleStatus;
import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.common.exception.DBException;
import com.iflytek.common.exception.DeleteFileException;
import com.iflytek.common.model.*;
import com.iflytek.common.utils.MyUtils;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.manager.api.ArticleService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/managerServiceContext.xml"})
public class ArticleTest {
    private Logger logger = LoggerFactory.getLogger(ArticleTest.class);
    @Autowired
    private ArticleService articleService;

    @Test
    public void getArticleList() {
        Map<String, Object> map = new HashMap<>();
        //map.put("mwId", "mw11011");
        map.put("startNum", 0);
        map.put("pageSize", 6);
        //map.put("timeOrder", "desc");
        map.put("hot", true);
        //map.put("openness", Openness.Open.getValue());
        //map.put("categoryName", "情感");
        // map.put("startTime", 0);
        //map.put("endTime", 10);
        //map.put("status", ArticleStatus.SCHEDULED_RELEASE.getValue());
        //map.put("title", "标题1");
        try {
            logger.info(JSON.toJSONString(articleService.getArticleList(map)) + "");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getArticleNumber() {
        Map<String, Object> map = new HashMap<>();
        map.put("mwId", "mw11012");
        map.put("openness", Openness.Open.getValue());
        logger.info("======>" + articleService.getArticleNumber(map));
    }

    @Test
    public void getArticle() {
        long oldTime = System.currentTimeMillis();
        logger.info(JSON.toJSONString(articleService.getArticle("35e19f544cbd4158a42afd7d3267012e")));
        long time = System.currentTimeMillis() - oldTime;
        logger.info(time + "----");
    }


    @Test
    public void writeArticle() {
        Article article = new Article();
        article.setArticleId(RandomUtils.getUUID());
        article.setCategoryName("文学");
        article.setCommentNumber(0);
        article.setContent("这是内容");
        article.setMusic("音乐");
        article.setOpenness(1);
        article.setPraiseNumber(10);
        article.setPwd("123");
        article.setReadingNumber(10);
        article.setReleaseTime(1111);
        article.setStatus(1);
        article.setTitle("title");
        article.setTitlePicture("titlePicture");
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId("mw11011");
        article.setUserBaseInfo(userBaseInfo);
        articleService.writeArticle(article);
    }

    @Test
    public void updatePraise() {
        articleService.updatePraise("0fd8cd0cf4e2411bbf67446672539ca3", 100);
    }

    @Test
    public void updateReadingNumber() {
        articleService.updateReadingNumber("0fd8cd0cf4e2411bbf67446672539ca3", 3);
    }

    @Test
    public void getReadingNumber() {
        logger.info(articleService.getReadingNumber("mw11011") + "");
    }

    @Test
    public void deleteArticle() throws DeleteFileException {
        logger.info(articleService.deleteArticle("1") + "");
    }

    @Test
    public void updateOpenness() {
        articleService.updateOpenness("3dc1eeec34744d5c9345a4d37dd77db7", Openness.Encrypt_Open.getValue(), "456");
    }

}
