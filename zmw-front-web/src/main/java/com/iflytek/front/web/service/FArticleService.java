package com.iflytek.front.web.service;
import com.iflytek.common.model.Article;
import com.iflytek.common.model.Result;
/**
 * @author AZhao
 */
public interface FArticleService {
    Result uploadArticle(Article article);

    Result<Article> getArticleByPwd(String articleId, String pwd, String key, String code);

    Result<Article> getArticle(String articleId);
}
