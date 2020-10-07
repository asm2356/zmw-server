package com.iflytek.search.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.model.OpLogRecord;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.search.api.OpLogService;
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
@ContextConfiguration(locations = {"classpath*:spring/searchServiceContext.xml"})
public class OpLogServiceTest {

    private Logger logger = LoggerFactory.getLogger(OpLogServiceTest.class);
    @Autowired
    private OpLogService opLogService;

    @Test
    public void insertLog() {
        OpLogRecord opLogRecord = new OpLogRecord();
        opLogRecord.setId(RandomUtils.getUUID());
        opLogRecord.setMwId("mw11011");
        opLogRecord.setOpTime(new Date().getTime());
        opLogRecord.setDescribe("66666666666666666");
        opLogRecord.setMethod("==================");
        opLogRecord.setRemoteIP("127.0.1.1");
        opLogRecord.setInputParams("name=4546&pwd=123");
        logger.info(JSON.toJSONString(opLogService.insertLog(opLogRecord)));
    }

    @Test
    public void deleteLog() {
        opLogService.deleteLog("0571c9d9986c4edfb167dcc036bcd1f9");
    }

    @Test
    public void getLog() {
        String condition = "{,\n" +
                "  \"time\":{\n" +
                "    \"startTime\":\"1559275218000\",\n" +
                "    \"endTime\":\"1561001049000\",\n" +
                "  }}";
        String condition2 = "{\n" +
                "\"method\":\"==================\"\n" +
                "}";
        logger.info(JSON.toJSONString(opLogService.getLog(0, 10, "mw11011", condition2)));
    }

    @Test
    public void getOpLogNumber() {
        String condition = "{\"describe\":\"66666666666666666\"}";
        logger.info(opLogService.getOpLogNumber("mw11011", null)+"");
    }
}
