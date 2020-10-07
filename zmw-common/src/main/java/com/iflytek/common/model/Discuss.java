package com.iflytek.common.model;

import java.io.Serializable;
import java.util.List;
/**
 * @author AZhao
 */
public class Discuss implements Serializable {
    private static final long serialVersionUID = -4079749605888856648L;
    /**
     * 主键
     */
    private String discussId;
    private long discussTime;
    private String articleId;
    private String content;
    /**
     * 评论者
     */
    private UserBaseInfo userBaseInfo;
    /***
     * 回复评论列表
     */
    private List<ReplyDiscuss> replyDiscussList = null;

    private int praiseNumber;

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        this.discussId = discussId;
    }


    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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

    public List<ReplyDiscuss> getReplyDiscussList() {
        return replyDiscussList;
    }

    public void setReplyDiscussList(List<ReplyDiscuss> replyDiscussList) {
        this.replyDiscussList = replyDiscussList;
    }

    public long getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(long discussTime) {
        this.discussTime = discussTime;
    }

    public int getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(int praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "discussId=" + discussId +
                ", discussTime=" + discussTime +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", userBaseInfo=" + userBaseInfo +
                ", replyDiscussList=" + replyDiscussList +
                ", praiseNumber=" + praiseNumber +
                '}';
    }
}
