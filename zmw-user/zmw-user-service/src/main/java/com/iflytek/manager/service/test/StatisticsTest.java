package com.iflytek.manager.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.enumeration.sys.StatisticsTimeType;
import com.iflytek.common.enumeration.sys.StatisticsType;
import com.iflytek.common.model.Statistics;
import com.iflytek.common.utils.HttpUtil;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.manager.api.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/managerServiceContext.xml"})
public class StatisticsTest {
    private Logger logger = LoggerFactory.getLogger(StatisticsTest.class);
    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void addStatistics() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        int week = 0;
        for (int i = 0, k = 1; i < 1000; i++, k++) {
            Statistics statistics = new Statistics();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            statistics.setId(RandomUtils.getUUID());
            Random random = new Random();
            int p = random.nextInt(5);
            int count = random.nextInt(100 * week * p + 1) + random.nextInt(200);
            statistics.setCount(count);
            statistics.setDate(calendar.getTime());
            statistics.setWeek(week);
            statistics.setType(StatisticsType.User_Sum.getValue());
            logger.info("-----" + statisticsService.addStatistics(statistics));
            if (k == 7) {
                week++;
                k = 1;
            }
        }
    }

    @Test
    public void getStatistics() {
        logger.info( JSON.toJSONString(statisticsService.getStatistics(StatisticsType.Active_Rate.getValue(), StatisticsTimeType.One_Year.getValue())));
    }

    @Test
    public void getTopActiveUser() {
        logger.info(JSON.toJSONString(statisticsService.getActivityRanking(10)));
    }
    @Test
    public void countTest(){
        Timer timer = new Timer();
        int period = 1000;//可以用日期来表示一天
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String result = HttpUtil.sendGet("http://127.0.0.1:8182/");
                System.out.println(result);
            }
        }, 1000, period);
    }
}
