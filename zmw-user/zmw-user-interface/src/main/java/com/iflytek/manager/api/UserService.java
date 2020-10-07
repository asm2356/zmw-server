package com.iflytek.manager.api;

import com.iflytek.common.model.Account;
/**
 * @author AZhao
 */
public interface UserService {
    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取用户所有点赞的个数
     */
    int getUserPraiseNumber(String mwId);

    void addUser(Account account) throws Exception;

}
