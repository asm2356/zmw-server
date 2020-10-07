package com.iflytek.manager.service.test;

import com.iflytek.common.model.LoginHistory;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.manager.api.LoginHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/managerServiceContext.xml"})
public class LoginHistoryTest {
    private Logger logger = LoggerFactory.getLogger(LoginHistory.class);
    @Autowired
    private LoginHistoryService loginHistoryService;
    @Test
    public void addLoginHistory() {
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setBrowser("11111111111111111111111111111");
        loginHistory.setId(RandomUtils.getUUID());
        loginHistory.setIp("127.211.0.11");
        loginHistory.setLoginEquipment("wer");
        loginHistory.setLoginTime(new Date().getTime());
        loginHistory.setMwId("mw11011");
        loginHistory.setPhone("2342312312423234");
        loginHistory.setLoginAddress("背北京");
        logger.info(loginHistoryService.addLoginHistory(loginHistory) + "");
    }

    @Test
    public void deleteLoginHistory() {
        logger.info(loginHistoryService.deleteLoginHistory("cba293586b6e464cb1a863e07f6354c5") + "");
    }

    @Test
    public void getLoginHistory() {
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setLoginEquipment("wer");
        logger.info( loginHistoryService.getLoginHistory(0, 10, loginHistory, 2000, new Date().getTime()) + "");
    }


    @Test
    public void getLoginHistoryNumber() {
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setLoginEquipment("wer");
        logger.info( loginHistoryService.getLoginHistoryNumber(loginHistory, 2000, new Date().getTime()) + "");
    }
}
