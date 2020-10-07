package com.iflytek.manager.api;

import com.iflytek.common.model.UserBaseInfo;

/**
 * @author AZhao
 */
public interface UserBaseInfoService {
    UserBaseInfo getUserBaseInfoById(String mwId);

    int updateUserBaseInfo(UserBaseInfo userBaseInfo);

    int addUserBaseInfo(UserBaseInfo userBaseInfo);
}
