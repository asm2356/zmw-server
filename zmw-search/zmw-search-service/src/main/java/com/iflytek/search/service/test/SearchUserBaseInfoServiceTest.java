package com.iflytek.search.service.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.search.api.SearchUserBaseInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/searchServiceContext.xml"})
public class SearchUserBaseInfoServiceTest {

    private Logger logger = LoggerFactory.getLogger(SearchUserBaseInfoService.class);
    @Autowired
    private SearchUserBaseInfoService searchUserBaseInfoService;

    @Test
    public void insertUserBaseInfo() {
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId("mw11013");
        userBaseInfo.setSex("男");
        userBaseInfo.setBirthday("2016-1-30");
        userBaseInfo.setWechat("1231211111111111");
        userBaseInfo.setIntroduction("777777");
        userBaseInfo.setAlias("小刘");
        userBaseInfo.setHeader("http://192.168.52.128:8888/group1/M00/00/02/wKjugFwLizCAdw34AAKd4Rwp2I8968.jpg");
        userBaseInfo.setCover("http://192.168.52.128:8888/group1/M00/00/02/wKjugFwLizCAdw34AAKd4Rwp2I8968.jpg");
        logger.info(JSON.toJSONString(searchUserBaseInfoService.insertUserBaseInfo(userBaseInfo)));
    }

    @Test
    public void deleteUserBaseInfo() {
        logger.info(searchUserBaseInfoService.deleteUserBaseInfo("mw11011") + "");
    }

    @Test
    public void getUserBaseInfo() {
        logger.info(JSON.toJSONString(searchUserBaseInfoService.getUserBaseInfo("mw11011")));
    }

    @Test
    public void getUserBaseInfoListByAttribute() {
        logger.info(JSON.toJSONString(searchUserBaseInfoService.getUserBaseInfoListByAttribute("小", 0, 10)));
    }

}
