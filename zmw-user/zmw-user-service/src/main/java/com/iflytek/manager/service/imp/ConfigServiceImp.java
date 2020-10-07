package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.Config;
import com.iflytek.manager.api.ConfigService;
import com.iflytek.manager.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author AZhao
 */
@Service
public class ConfigServiceImp implements ConfigService {
    @Autowired
    private ConfigDao configDao;
    @Override
    public int addConfig(Config config) throws DBException {
        try {
            return configDao.insert(config);
        } catch (Exception e) {
            throw new DBException(ResultCode.Data_Duplicate.getCode(), ResultCode.Data_Duplicate.getMessage());
        }
    }

    @Override
    public int deleteConfig(String key) {
        return configDao.deleteById(key);
    }

    @Override
    public int updateConfig(Config config) {
        return configDao.updateById(config);
    }

    @Override
    public List<Config> getConfigList(int startNum, int pageSize, String key, String value, String describe) {
        return configDao.getConfigList(startNum, pageSize, key, value, describe);
    }

    @Override
    public int getConfigNumber(String key, String value, String describe) {
        return configDao.getConfigNumber(key, value, describe);
    }
}
