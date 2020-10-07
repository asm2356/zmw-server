package com.iflytek.common.model.vo;

import com.iflytek.common.model.Article;
import com.iflytek.common.model.UserBaseInfo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author AZhao
 */
public class UserComment implements Serializable,Cloneable {
    private static final long serialVersionUID = -8293624360943509043L;
    /***
     * 主键
     */
    private String id;
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

    private int praiseNumber;

    private Article article;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(long time) {
        this.discussTime = time;
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

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        this.discussId = discussId;
    }

    public UserBaseInfo getTargetUserBaseInfo() {
        return targetUserBaseInfo;
    }

    public void setTargetUserBaseInfo(UserBaseInfo targetUserBaseInfo) {
        this.targetUserBaseInfo = targetUserBaseInfo;
    }

    public String getToContent() {
        return toContent;
    }

    public void setToContent(String toContent) {
        this.toContent = toContent;
    }

    public int getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(int praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserComment that = (UserComment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "id='" + id + '\'' +
                ", discussTime=" + discussTime +
                ", content='" + content + '\'' +
                ", userBaseInfo=" + userBaseInfo +
                ", discussId='" + discussId + '\'' +
                ", targetUserBaseInfo=" + targetUserBaseInfo +
                ", toContent='" + toContent + '\'' +
                ", praiseNumber=" + praiseNumber +
                ", article=" + article +
                '}';
    }

}
