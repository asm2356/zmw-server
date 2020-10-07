package com.iflytek.search.api;


import com.iflytek.common.model.UserBaseInfo;

import java.util.List;

/**
 * @author AZhao
 */
public interface SearchUserBaseInfoService {
    int insertUserBaseInfo(UserBaseInfo userBaseInfo);

    int updateUserBaseInfo(UserBaseInfo userBaseInfo);

    int deleteUserBaseInfo(String mwId);

    /**
     * 根据id进行搜索
     *
     * @param mwId
     * @return
     */
    UserBaseInfo getUserBaseInfo(String mwId);

    /**
     * 搜索 alias 或者 mwId
     *
     * @param item
     * @param startNum
     * @param pageSize
     * @return
     */
    List getUserBaseInfoListByAttribute(String item, long startNum, int pageSize);
}
