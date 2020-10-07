package com.iflytek.manager.service.test;

import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.manager.api.UserService;
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
public class UserTest {
    private Logger logger = LoggerFactory.getLogger(UserTest.class);
    @Autowired
    private UserService userService;

    @Test
    public void getUserPraiseNumber() {
        logger.info(userService.getUserPraiseNumber("mw11011") + "");
    }

}
