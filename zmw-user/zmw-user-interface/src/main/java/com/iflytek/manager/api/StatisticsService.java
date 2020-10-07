package com.iflytek.manager.api;

import com.iflytek.common.model.Statistics;
import com.iflytek.common.model.vo.ActiveUser;

import java.util.List;

/**
 * @author AZhao
 */
public interface StatisticsService {
    int addStatistics(Statistics statistics);

    /***
     * @param timeType
     *          enum statisticsType
     *
     * @return
     */
    List<Statistics> getStatistics(int statisticsType, int timeType);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取活跃率前几位的用户
     */
    List<ActiveUser> getActivityRanking(int count);

}
