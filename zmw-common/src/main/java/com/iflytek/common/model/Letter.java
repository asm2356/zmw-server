package com.iflytek.common.model;

import com.iflytek.common.enumeration.user.LetterStatus;

import java.io.Serializable;

/**
 * @author AZhao
 */
public class Letter implements Serializable {
    private static final long serialVersionUID = -1781071037150395810L;
    private String id;
    private UserBaseInfo userBaseInfo;
    private UserBaseInfo toUserBaseInfo;
    private long sendTime;
    private String content;
    private int isRead = LetterStatus.Unread.getValue();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    public UserBaseInfo getToUserBaseInfo() {
        return toUserBaseInfo;
    }

    public void setToUserBaseInfo(UserBaseInfo toUserBaseInfo) {
        this.toUserBaseInfo = toUserBaseInfo;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", userBaseInfo=" + userBaseInfo +
                ", toUserBaseInfo=" + toUserBaseInfo +
                ", sendTime=" + sendTime +
                ", content='" + content + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
