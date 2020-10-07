package com.iflytek.common.model.dto;

import com.iflytek.common.model.Article;
import com.iflytek.common.model.Discuss;
import com.iflytek.common.model.ReplyDiscuss;

import java.io.Serializable;
/**
 * @author AZhao
 */
public class UserArticleDiscuss implements Serializable {
    private static final long serialVersionUID = 1936891709501254165L;
    private Article article;
    private Discuss discuss;
    private ReplyDiscuss replyDiscuss;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Discuss getDiscuss() {
        return discuss;
    }

    public void setDiscuss(Discuss discuss) {
        this.discuss = discuss;
    }

    public ReplyDiscuss getReplyDiscuss() {
        return replyDiscuss;
    }

    public void setReplyDiscuss(ReplyDiscuss replyDiscuss) {
        this.replyDiscuss = replyDiscuss;
    }

    @Override
    public String toString() {
        return "UserArticleDiscuss{" +
                "article=" + article +
                ", discuss=" + discuss +
                ", replyDiscuss=" + replyDiscuss +
                '}';
    }
}
