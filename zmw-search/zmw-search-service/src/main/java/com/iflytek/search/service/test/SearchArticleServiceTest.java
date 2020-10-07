package com.iflytek.search.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.enumeration.user.ArticleStatus;
import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.common.model.*;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.search.api.SearchArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/searchServiceContext.xml"})
public class SearchArticleServiceTest {

    @Autowired
    private SearchArticleService searchArticleService;
    private Logger logger = LoggerFactory.getLogger(SearchArticleServiceTest.class);

    @Test
    public void insertArticle() {
        Article article = new Article();
        article.setArticleId(RandomUtils.getUUID());
        article.setTitle("这是标题色色");
        article.setTitlePicture("http://www.baidu.com");
        article.setOpenness(Openness.Open.getValue());
        article.setReleaseTime(new Date().getTime());
        article.setCategoryName("文学");
        article.setPwd("123123");
        article.setPraiseNumber(10);
        article.setReadingNumber(1000);
        article.setCommentNumber(10);
        article.setContent("xxxxxxx");
        article.setMusic("yyyyyyyy");
        article.setStatus(ArticleStatus.HAS_RELEASE.getValue());
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId("mw11012");
        userBaseInfo.setAlias("小张");
        userBaseInfo.setBirthday("222222");
        userBaseInfo.setCover("3333333333");
        userBaseInfo.setHeader("4444444444");
        userBaseInfo.setIntroduction("5555555555");
        userBaseInfo.setSex("女");
        userBaseInfo.setWechat("7777777777");
        article.setUserBaseInfo(userBaseInfo);
        logger.info(searchArticleService.insertArticle(article) + "");
    }


    @Test
    public void updateArticle(){
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","这是类别");
        searchArticleService.updateArticle("f018ee0d48c441b4bffb2f3bbe462414",map);
    }

    @Test
    public void updateOpenness() {
        Map<String,Object> map = new HashMap<>();
        map.put("openness",3);
        map.put("pwd","123456");
        searchArticleService.updateArticle("3dc1eeec34744d5c9345a4d37dd77db7", map);
    }
    @Test
    public void deleteArticle() {
        logger.info(searchArticleService.deleteArticle("396f0c008576425eb492b79adee310f3") + "");
    }

    @Test
    public void getArticle() {
        logger.info(JSON.toJSONString(searchArticleService.getArticle("f018ee0d48c441b4bffb2f3bbe462414")));
    }

    @Test
    public void getArticleListByTitle() {
        logger.info(JSON.toJSONString(searchArticleService.getArticleListByTitle("标题")));
    }
}
