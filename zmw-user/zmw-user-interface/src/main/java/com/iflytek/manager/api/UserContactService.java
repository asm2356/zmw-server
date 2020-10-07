package com.iflytek.manager.api;


import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.UserBaseInfo;

import java.util.List;

/**
 * @author AZhao
 */
public interface UserContactService {

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取用户关注的数量
     */
    int getNoticerNumber(String mwId);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取作者粉丝的数目
     */
    int getFansNumber(String mwId);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 查询用户关注的
     */
    List<UserBaseInfo> getNoticerList(String mwId, long startNum, int pageSize);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 查询粉丝的数目
     */
    List<UserBaseInfo> getFansList(String mwId, long startNum, int pageSize);


    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 关注作者
     */
    boolean concernedAuthor(String mwId, String noticerMwId) throws DBException;


    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 取消关注作者
     */
    boolean cancelConcernedAuthor(String mwId, String noticerMwId);
}
