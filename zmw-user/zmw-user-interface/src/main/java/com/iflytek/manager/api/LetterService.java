package com.iflytek.manager.api;

import com.iflytek.common.model.Letter;

import java.util.List;

/**
 * @author AZhao
 */
public interface LetterService {

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe
     */
    int writeLetter(Letter letter);

    /**
     * @param bothLetter true 获取双方的消息,false 只获取对方发的消息
     * @return
     * @author zgzhao
     * @describe 时间按照降序排序
     */
    List<Letter> getLetterList(long startNum, int pageSize, String mwId, int isRead, boolean bothLetter);

    int deleteLetter(String id);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 阅读消息标记为已读
     */
    int readLetter(String id);

    /**
     * @param bothLetter true 获取双方的消息,false 只获取对方发的消息
     * @return
     * @author zgzhao
     * @describe
     */
    int getLetterNumber(String mwId, boolean bothLetter);
}
