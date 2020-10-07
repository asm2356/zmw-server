package com.iflytek.manager.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.exception.DBException;
import com.iflytek.manager.api.CollectService;
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
public class CollectTest {
    private Logger logger = LoggerFactory.getLogger(CollectTest.class);
    @Autowired
    private CollectService collectService;

    @Test
    public void getCollectArticleList() {
        logger.info(JSON.toJSONString(collectService.getCollectArticleList("mw11011", 0, 100)) + "");
    }

    @Test
    public void getCollectArticleNumber() {
        logger.info(collectService.getCollectArticleNumber("mw11012") + "");
    }

    @Test
    public void collectArticle() {
        try {
            logger.info(collectService.collectArticle("mw11011", "2") + "");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelCollectArticle() {
        logger.info(collectService.cancelCollectArticle("mw11011", "2") + "");
    }

}
