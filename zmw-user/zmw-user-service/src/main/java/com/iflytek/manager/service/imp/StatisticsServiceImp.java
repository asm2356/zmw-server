package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.enumeration.sys.StatisticsTimeType;
import com.iflytek.common.model.Statistics;
import com.iflytek.common.model.vo.ActiveUser;
import com.iflytek.config.service.redis.RedisService;
import com.iflytek.manager.api.StatisticsService;
import com.iflytek.manager.dao.StatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author AZhao
 */
@Service
public class StatisticsServiceImp implements StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;

    @Autowired
    private RedisService redisService;

    @Override
    @CacheEvict(value = "statistics")
    public int addStatistics(Statistics statistics) {
        return statisticsDao.insert(statistics);
    }


    @Override
    @Cacheable(value = "statistics")
    public List<Statistics> getStatistics(int statisticsType, int timeType) {
        List<Statistics> list = statisticsDao.getStatistics(timeType, statisticsType);
        String statisticsKey = RedisPrefix.STATISTICS_ONE_YEAR + statisticsType;

        if (timeType == StatisticsTimeType.One_Year.getValue()) {
            if (!redisService.hasKey(statisticsKey)) {
                //将一年的天数分割成月份累加储存
                Calendar calendar = Calendar.getInstance();
                int currentMonth = calendar.get(Calendar.MONTH) + 1;
                List<Statistics> newStatisticList = new ArrayList<>();
                List<Statistics> tempList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Statistics statistics = list.get(i);
                    //格式去掉day
                    calendar = Calendar.getInstance();
                    calendar.setTime(statistics.getDate());
                    int month = calendar.get(Calendar.MONTH) + 1;
                    //新的月份重新计算
                    if (currentMonth != month) {
                        Statistics statisticsTemp = new Statistics();
                        int count = 0;
                        for (int j = 0; j < tempList.size(); j++) {
                            count += tempList.get(j).getCount();
                        }
                        tempList.clear();
                        statisticsTemp.setCount(count);
                        statisticsTemp.setDate(statistics.getDate());
                        statisticsTemp.setWeek(statistics.getWeek());
                        newStatisticList.add(statisticsTemp);
                        currentMonth = month;
                        tempList.add(statistics);//当前月份的第一天
                    } else {
                        tempList.add(statistics);
                    }
                }
                //缓存一个月
                redisService.set(statisticsKey, newStatisticList, 31 * 24 * 3600);
                return newStatisticList;
            } else {
                return (List<Statistics>) redisService.get(statisticsKey);
            }
        }
        return list;
    }

    @Override
    @Cacheable(value = "getActivityRanking",key = "#count")
    public List<ActiveUser> getActivityRanking(int count) {
        return statisticsDao.getActivityRanking(count);
    }


}
