package com.iflytek.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author AZhao
 */
public class Collector implements Serializable {
    private static final long serialVersionUID = -2608331226952175372L;
    private UserBaseInfo userBaseInfo;
    private List<Article> article;

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    @Override
    public String toString() {
        return "Collector{" +
                "userBaseInfo=" + userBaseInfo +
                ", article=" + article +
                '}';
    }
}
