package com.iflytek.manager.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.constant.UserConstant;
import com.iflytek.common.exception.DBException;
import com.iflytek.manager.api.UserContactService;
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
public class UserContactTest {
    private Logger logger = LoggerFactory.getLogger(UserConstant.class);
    @Autowired
    private UserContactService userContactService;

    @Test
    public void getFansNumber() {
        logger.info(userContactService.getFansNumber("mw11011") + "");
    }

    @Test
    public void getNoticerNumber() {
        logger.info(userContactService.getNoticerNumber("mw11011") + "");
    }


    @Test
    public void getNoticerList() {
        logger.info(JSON.toJSONString(userContactService.getNoticerList("mw11011", 0, 10)) + "");
    }

    @Test
    public void getFansList() {
        logger.info(JSON.toJSONString(userContactService.getFansList("mw11011", 0, 10)) + "");
    }

    @Test
    public void concernedAuthor() {
        try {
            logger.info(userContactService.concernedAuthor("mw11011", "mw11012") + "");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelConcernedAuthor() {
        logger.info(userContactService.cancelConcernedAuthor("mw11011", "mw11012") + "");
    }
}
