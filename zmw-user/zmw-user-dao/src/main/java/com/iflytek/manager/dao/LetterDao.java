package com.iflytek.manager.dao;

import com.iflytek.common.model.Letter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
public interface LetterDao {

    int insertLetter(Letter letter);

    /**
     * @param long startNum, int pageSize, String mwId
     * @return
     * @author zgzhao
     * @describe 时间按照降序排序
     */
    List<Letter> getLetterList(Map<String, Object> map);

    int deleteLetter(String id);

    /**
     * @param map id letterStatus
     * @return
     * @author zgzhao
     * @describe
     */
    int readLetter(@Param("id") String id, @Param("letterStatus")int letterStatus);

    int getLetterNumber(@Param("mwId")String mwId, @Param("bothLetter")boolean bothLetter);
}
