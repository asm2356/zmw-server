package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.model.LoginHistory;
import com.iflytek.manager.api.LoginHistoryService;
import com.iflytek.manager.dao.LoginHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author AZhao
 */
@Service
public class LoginHistoryServiceImp implements LoginHistoryService {
    @Autowired
    private LoginHistoryDao loginHistoryDao;

    @Override
    public int addLoginHistory(LoginHistory loginHistory) {
        return loginHistoryDao.insert(loginHistory);
    }

    @Override
    public int deleteLoginHistory(String id) {
        return loginHistoryDao.deleteById(id);
    }

    @Override
    public List<LoginHistory> getLoginHistory(int startNum, int pageSize,
                                              LoginHistory loginHistory, long startTime, long endTime) {
        return loginHistoryDao.getLoginHistory(startNum, pageSize, loginHistory, startTime, endTime);
    }

    @Override
    public int getLoginHistoryNumber(LoginHistory loginHistory, long startTime, long endTime) {
        return loginHistoryDao.getLoginHistoryNumber(loginHistory, startTime, endTime);
    }
}
