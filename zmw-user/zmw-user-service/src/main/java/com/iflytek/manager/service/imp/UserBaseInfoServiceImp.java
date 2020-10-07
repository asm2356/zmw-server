package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.manager.api.UserBaseInfoService;
import com.iflytek.manager.dao.UserBaseInfoDao;
import com.iflytek.search.api.SearchUserBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author AZhao
 */
@Service
public class UserBaseInfoServiceImp implements UserBaseInfoService {
    @Autowired
    private UserBaseInfoDao userBaseInfoDao;

    @Reference
    private SearchUserBaseInfoService searchUserBaseInfoService;

    @Override
    @Cacheable(value = "getUserBaseInfoById", key = "#mwId")
    public UserBaseInfo getUserBaseInfoById(String mwId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userBaseInfoDao.selectById(mwId);
    }

    @Override
    @CacheEvict(value = "getUserBaseInfoById", key = "#userBaseInfo.mwId")
    public int updateUserBaseInfo(UserBaseInfo userBaseInfo) {
        searchUserBaseInfoService.updateUserBaseInfo(userBaseInfo);
        return userBaseInfoDao.updateById(userBaseInfo);
    }

    @Override
    public int addUserBaseInfo(UserBaseInfo userBaseInfo) {
        searchUserBaseInfoService.insertUserBaseInfo(userBaseInfo);
        return userBaseInfoDao.insert(userBaseInfo);
    }
}
