package com.iflytek.manager.api;

import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.Config;

import java.util.List;

/**
 * @author AZhao
 */
public interface ConfigService {
    /**
     * @param config key必须唯一
     * @return
     */
    int addConfig(Config config) throws DBException;


    int deleteConfig(String key);

    /**
     * @param config 根据可以 进行更新
     * @return
     */
    int updateConfig(Config config);

    /**
     * key ,value ,describe 支持模糊查询
     *
     * @param startNum 必须
     * @param pageSize 必须
     * @param key      非必须
     * @param value    非必须
     * @param describe 非必须
     * @return
     */
    List<Config> getConfigList(
            int startNum,
            int pageSize,
            String key,
            String value,
            String describe);

    /**
     * key ,value ,describe 支持模糊查询
     *
     * @param key      非必须
     * @param value    非必须
     * @param describe 非必须
     * @return
     */
    int getConfigNumber(String key,
                        String value,
                        String describe);
}
