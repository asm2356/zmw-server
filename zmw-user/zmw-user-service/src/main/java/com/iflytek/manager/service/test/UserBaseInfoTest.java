package com.iflytek.manager.service.test;

import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.manager.api.UserBaseInfoService;
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
@ContextConfiguration(locations = {"classpath*:spring/managerServiceContext.xml"})
public class UserBaseInfoTest {
    private Logger logger = LoggerFactory.getLogger(UserBaseInfoTest.class);
    @Autowired
    private UserBaseInfoService userBaseInfoService;

    @Test
    public void getUserBaseInfoById() {
        logger.info(userBaseInfoService.getUserBaseInfoById("mw11011") + "");
    }
    @Test
    public void updateUserBaseInfo() {
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId("mw11024");
        userBaseInfo.setAlias("11111");
        userBaseInfo.setBirthday("222222");
        userBaseInfo.setCover("3333333333");
        userBaseInfo.setHeader("4444444444");
        userBaseInfo.setIntroduction("5555555555");
        userBaseInfo.setSex("2");
        userBaseInfo.setWechat("7777777777");
        try {
            logger.info(userBaseInfoService.updateUserBaseInfo(userBaseInfo) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUserBaseInfo() {
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId("mw11024");
        userBaseInfo.setAlias("11111");
        userBaseInfo.setBirthday("222222");
        userBaseInfo.setCover("3333333333");
        userBaseInfo.setHeader("4444444444");
        userBaseInfo.setIntroduction("5555555555");
        userBaseInfo.setSex("2");
        userBaseInfo.setWechat("7777777777");
        try {
            logger.info(userBaseInfoService.addUserBaseInfo(userBaseInfo) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
