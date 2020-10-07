package com.iflytek.search.api;

import com.iflytek.common.model.Article;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
public interface SearchArticleService {
    int insertArticle(Article article);

    int deleteArticle(String articleId);

    Article getArticle(String articleId);

    void updateArticle(String id,Map<String,Object> map) ;

    /**
     * 对title进行模糊搜索并且高亮显示
     * @param title
     * @return
     */
    List<Article> getArticleListByTitle(String title);

    int getArticleNumberByTitle(String title);
}
