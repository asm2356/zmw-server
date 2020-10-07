package com.iflytek.search.api;

import com.iflytek.common.model.OpLogRecord;

import java.util.List;

/**
 * @author AZhao
 */
public interface OpLogService {
    /**
     * 返回生成id
     * @param opLogRecord
     * @return
     */
    int insertLog(OpLogRecord opLogRecord);

    int deleteLog(String id);

    List getLog(int startNum, int pageSize, String mwId, String condition);

    /**
     * 获取某个用户操作日志的数量
     * @param mwId
     * @param condition
     * @return
     */
    int getOpLogNumber(String mwId, String condition);
}
