package com.iflytek.manager.dao;

import com.iflytek.common.model.UserBaseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Repository
public interface UserContactDao {
    int getNoticerNumber(String mwId);

    int getFansNumber(String mwId);

    /**
     * @param map String mwId,
     *            long startNum,
     *            int pageSize
     * @return
     * @author zgzhao
     * @describe
     */
    List<UserBaseInfo> getNoticerList(Map<String, Object> map);

    /**
     * @param map String mwId,
     *            long startNum,
     *            int pageSize
     * @return
     * @author zgzhao
     * @describe
     */
    List<UserBaseInfo> getFansList(Map<String, Object> map);

    /**
     * @param map String mwId,
     *            String noticerMwId
     * @return
     * @author zgzhao
     * @describe
     */
    int addContact(Map<String, String> map);

    /**
     * @param map String mwId,
     *            String noticerMwId
     * @return
     * @author zgzhao
     * @describe
     */
    int deleteContact(Map<String, String> map);
}
