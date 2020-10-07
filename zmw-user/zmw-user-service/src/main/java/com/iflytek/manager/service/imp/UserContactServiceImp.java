package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.manager.api.UserContactService;
import com.iflytek.manager.dao.UserContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Service
public class UserContactServiceImp implements UserContactService {

    @Autowired
    private UserContactDao userContactDao;

    @Override
    public int getNoticerNumber(String mwId) {
        return userContactDao.getNoticerNumber(mwId);
    }

    @Override
    public int getFansNumber(String mwId) {
        return userContactDao.getFansNumber(mwId);
    }

    @Override
    public List<UserBaseInfo> getNoticerList(final String mwId, final long startNum, final int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = -7515383677143223159L;

            {
                put("mwId", mwId);
                put("startNum", startNum);
                put("pageSize", pageSize);
            }
        };
        return userContactDao.getNoticerList(map);
    }

    @Override
    public List<UserBaseInfo> getFansList(final String mwId, final long startNum, final int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = -7515383677143223159L;

            {
                put("mwId", mwId);
                put("startNum", startNum);
                put("pageSize", pageSize);
            }
        };
        return userContactDao.getFansList(map);
    }

    @Override
    public boolean concernedAuthor(final String mwId, final String noticerMwId) throws DBException {
        Map<String, String> map = new HashMap<String, String>() {
            private static final long serialVersionUID = -5532168034423474927L;

            {
                put("mwId", mwId);
                put("noticerMwId", noticerMwId);
            }
        };
        try {
            return userContactDao.addContact(map) > 0;
        } catch (DuplicateKeyException e) {
            return true;
        }
    }

    @Override
    public boolean cancelConcernedAuthor(final String mwId, final String noticerMwId) {
        Map<String, String> map = new HashMap<String, String>() {
            private static final long serialVersionUID = -5532168034423474927L;

            {
                put("mwId", mwId);
                put("noticerMwId", noticerMwId);
            }
        };
        return userContactDao.deleteContact(map) > 0;
    }


}
