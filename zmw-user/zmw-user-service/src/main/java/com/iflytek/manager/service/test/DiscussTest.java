package com.iflytek.manager.service.test;

import com.alibaba.fastjson.JSONObject;
import com.iflytek.common.model.Discuss;
import com.iflytek.common.model.ReplyDiscuss;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.manager.api.DiscussService;
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
public class DiscussTest {
    private Logger logger = LoggerFactory.getLogger(DiscussTest.class);
    @Autowired
    private DiscussService discussService;

    @Test
    public void getDiscussList() {
        logger.info(JSONObject.toJSONString(discussService.getDiscussList("2cd968b1779d487e949a56e4ef34f7bc", 0, 10)) + "");
    }


    @Test
    public void getOnlyDiscussList() {
        Map<String, Object> map = new HashMap<>();
        map.put("articleId", "85328cf8590949559152a84d79fe4c81");
        map.put("startNum", 0);
        map.put("pageSize", 10);
        //map.put("mwId","mw11012");
        //map.put("content","");
        map.put("discussId", "1");
        logger.info(JSONObject.toJSONString(discussService.getOnlyDiscussList(map)) + "");

    }

    @Test
    public void getOnlyDiscussCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("articleId", "0bd738ca4dda42829ec4029ab9465109");
        map.put("startNum", 0);
        map.put("pageSize", 10);
        //map.put("mwId","mw11012");
        //map.put("content", "");
        //map.put("discussId", "1");
        logger.info(JSONObject.toJSONString(discussService.getOnlyDiscussCount(map)) + "");
    }

    @Test
    public void getReplyDiscussList() {
        Map<String, Object> map = new HashMap<>();
        map.put("replyDiscussId", "1");
        map.put("discussId", "2");
        map.put("mwId", "3");
        map.put("toContent", "4");
        map.put("replyContent", "5");
        map.put("startNum", 0);
        map.put("pageSize", 10);
        logger.info(JSONObject.toJSONString(discussService.getReplyDiscussList(map)) + "");
    }

    @Test
    public void getReplyDiscussCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("", "");
        logger.info(JSONObject.toJSONString(discussService.getReplyDiscussCount(map)) + "");
    }

    @Test
    public void writeDiscuss() {
        Discuss discuss = new Discuss();
        discuss.setArticleId("1");
        discuss.setContent("12312212");
        discuss.setDiscussTime(1234545);
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId("mw11011");
        discuss.setUserBaseInfo(userBaseInfo);
        discuss.setPraiseNumber(123123);
        logger.info(discussService.writeDiscuss(discuss) + "");
    }

    @Test
    public void writeReplyDiscuss() {
        ReplyDiscuss replyDiscuss = new ReplyDiscuss();
        replyDiscuss.setContent("conss");
        replyDiscuss.setDiscussTime(12312);
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId("mw11011");
        replyDiscuss.setUserBaseInfo(userBaseInfo);
        replyDiscuss.setReplyPraiseNumber(1121);
        userBaseInfo.setMwId("mw11012");
        replyDiscuss.setToContent("0000");
        replyDiscuss.setTargetUserBaseInfo(userBaseInfo);
        logger.info(discussService.writeReplyDiscuss(replyDiscuss) + "");
    }

    @Test
    public void deleteDiscuss() {
        logger.info(discussService.deleteDiscuss("1") + "");
    }

    @Test
    public void deleteReplyDiscuss() {
        logger.info(discussService.deleteReplyDiscuss("1") + "");
    }


    @Test
    public void updateDiscussPraise() {
        logger.info(discussService.updateDiscussPraise("2", 111) + "");
    }

    @Test
    public void updateReplyDiscussPraise() {
        logger.info(discussService.updateReplyDiscussPraise("2", 666) + "");
    }

    @Test
    public void getUserArticleDiscussList() {
        logger.info(JSONObject.toJSONString(discussService.getUserCommentList("mw11011", 0, 20)));
    }

    @Test
    public void getUserDiscussNumber() {
        logger.info(discussService.getUserDiscussNumber("mw11011") + "");
    }

    @Test
    public void deleteDiscussByArticleId() {
        logger.info(discussService.deleteDiscussByArticleId("35") + "");
    }


}
