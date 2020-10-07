package com.iflytek.manager.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.enumeration.user.LetterStatus;
import com.iflytek.common.model.Letter;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.manager.api.LetterService;
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
public class LetterTest {
    private Logger logger = LoggerFactory.getLogger(LetterTest.class);
    @Autowired
    private LetterService letterService;

    @Test
    public void insertLetter() {
        Letter letter = new Letter();
        letter.setId(RandomUtils.getUUID());
        letter.setContent("======222=222=======");
        letter.setIsRead(2);
        letter.setSendTime(22222);
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId("mw11011");
        UserBaseInfo toUserBaseInfo = new UserBaseInfo();
        toUserBaseInfo.setMwId("mw11012");
        letter.setUserBaseInfo(userBaseInfo);
        letter.setToUserBaseInfo(toUserBaseInfo);
        logger.info(letterService.writeLetter(letter) + "");
    }

    @Test
    public void getLetterList() {
        logger.info(JSON.toJSONString(letterService.getLetterList(0, 10, "mw11013", LetterStatus.All.getValue(), true)));
    }

    @Test
    public void deleteLetter() {
        logger.info(letterService.deleteLetter("e6de3f8d868548539223e65a11fcaac5") + "");
    }

    @Test
    public void readLetter() {
        logger.info(letterService.readLetter("e6de3f8d868548539223e65a11fcaac5") + "");
    }

    @Test
    public void getLetterNumber() {
        logger.info(letterService.getLetterNumber("mw11011", false) + "");
    }
}
