package com.iflytek.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author AZhao
 */
@TableName(value = "article")
public class Article implements Serializable {
    private static final long serialVersionUID = -4453649474867655584L;
    @TableId(value = "article_id")
    private String articleId;
    @TableField(value = "title")
    private String title;
    @TableField(value = "title_picture")
    private String titlePicture;
    @TableField(value = "openness")
    private int openness;
    @TableField(value = "release_time")
    private long releaseTime;
    @TableField(value = "category_name")
    private String categoryName;
    @TableField(value = "pwd")
    private String pwd;
    @TableField(value = "praise_number")
    private int praiseNumber;
    @TableField(value = "reading_number")
    private int readingNumber;
    @TableField(exist =false)
    private int commentNumber;
    @TableField(value = "content")
    private String content;
    @TableField(value = "music")
    private String music;
    @TableField(value = "status")
    private int status;
    @TableField(exist =false)
    private UserBaseInfo userBaseInfo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlePicture() {
        return titlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        this.titlePicture = titlePicture;
    }


    public int getOpenness() {
        return openness;
    }

    public void setOpenness(int openness) {
        this.openness = openness;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(int praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public int getReadingNumber() {
        return readingNumber;
    }

    public void setReadingNumber(int readingNumber) {
        this.readingNumber = readingNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", title='" + title + '\'' +
                ", titlePicture='" + titlePicture + '\'' +
                ", openness=" + openness +
                ", releaseTime=" + releaseTime +
                ", categoryName='" + categoryName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", praiseNumber=" + praiseNumber +
                ", readingNumber=" + readingNumber +
                ", commentNumber=" + commentNumber +
                ", content='" + content + '\'' +
                ", music='" + music + '\'' +
                ", status=" + status +
                ", userBaseInfo=" + userBaseInfo +
                '}';
    }
}
