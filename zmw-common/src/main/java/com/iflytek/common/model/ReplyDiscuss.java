package com.iflytek.common.model;

import java.io.Serializable;
/**
 * @author AZhao
 */
public class ReplyDiscuss implements Serializable {
    private static final long serialVersionUID = -8293624360943509043L;
    /***
     * 主键
     */
    private String replyDiscussId;
    private long discussTime;
    private String content;
    /**
     * 回复评论人
     */
    private UserBaseInfo userBaseInfo;
    /**
     * 回复评论关联的主键
     */
    private String discussId;

    /**
     * 回复目标人id
     */
    private UserBaseInfo targetUserBaseInfo;

    /**
     * 回复目标人内容
     */
    private String toContent;
    private int  replyPraiseNumber;
    public String getReplyDiscussId() {
        return replyDiscussId;
    }

    public void setReplyDiscussId(String replyDiscussId) {
        this.replyDiscussId = replyDiscussId;
    }

    public long getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(long discussTime) {
        this.discussTime = discussTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    public UserBaseInfo getTargetUserBaseInfo() {
        return targetUserBaseInfo;
    }

    public void setTargetUserBaseInfo(UserBaseInfo targetUserBaseInfo) {
        this.targetUserBaseInfo = targetUserBaseInfo;
    }

    public int getReplyPraiseNumber() {
        return replyPraiseNumber;
    }

    public void setReplyPraiseNumber(int replyPraiseNumber) {
        this.replyPraiseNumber = replyPraiseNumber;
    }

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        this.discussId = discussId;
    }

    public String getToContent() {
        return toContent;
    }

    public void setToContent(String toContent) {
        this.toContent = toContent;
    }

    @Override
    public String toString() {
        return "ReplyDiscuss{" +
                "replyDiscussId=" + replyDiscussId +
                ", discussTime=" + discussTime +
                ", content='" + content + '\'' +
                ", userBaseInfo=" + userBaseInfo +
                ", discussId=" + discussId +
                ", targetUserBaseInfo=" + targetUserBaseInfo +
                ", toContent='" + toContent + '\'' +
                ", replyPraiseNumber=" + replyPraiseNumber +
                '}';
    }
}
