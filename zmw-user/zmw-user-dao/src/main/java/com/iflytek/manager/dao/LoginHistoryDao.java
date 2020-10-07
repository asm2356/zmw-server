package com.iflytek.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hazelcast.core.BaseMap;
import com.iflytek.common.model.LoginHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AZhao
 */
@Repository
public interface LoginHistoryDao extends BaseMapper<LoginHistory> {
    List<LoginHistory> getLoginHistory(@Param("startNum") int startNum,
                                       @Param("pageSize") int pageSize,
                                       @Param("loginHistory") LoginHistory loginHistory,
                                       @Param("startTime") long startTime,
                                       @Param("endTime") long endTime);

    int getLoginHistoryNumber(@Param("loginHistory") LoginHistory loginHistory,
                              @Param("startTime") long startTime,
                              @Param("endTime") long endTime);


}
