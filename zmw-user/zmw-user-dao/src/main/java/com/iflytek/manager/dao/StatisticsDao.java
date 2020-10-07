package com.iflytek.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iflytek.common.model.Statistics;
import com.iflytek.common.model.vo.ActiveUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AZhao
 */
@Repository
public interface StatisticsDao  extends BaseMapper<Statistics> {
    List<Statistics> getStatistics(@Param("timeType") int timeType,
                                   @Param("statisticsType") int statisticsType);

    List<ActiveUser> getActivityRanking(int count);
}
