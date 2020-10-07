package com.iflytek.manager.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hazelcast.core.BaseMap;
import com.iflytek.common.model.LoginHistory;

import java.util.List;

/**
 * @author AZhao
 */
public interface LoginHistoryService  {
    int addLoginHistory(LoginHistory loginHistory);

    int deleteLoginHistory(String id);

    /**
     * @param startNum     必须
     * @param pageSize     必须
     * @param loginHistory 可选
     * @param startTime    登录时间开始区间 可选 -1 查询所有
     * @param endTime      登录时间结束区间 可选 -1 查询所有
     * @return 返回登录历史记录
     */
    List<LoginHistory> getLoginHistory(int startNum, int pageSize, LoginHistory loginHistory, long startTime, long endTime);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 按照条件搜索登录历史记录条数
     */
    int getLoginHistoryNumber(LoginHistory loginHistory, long startTime, long endTime);
}
