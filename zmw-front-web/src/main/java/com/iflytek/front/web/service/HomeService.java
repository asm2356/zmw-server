package com.iflytek.front.web.service;

import com.iflytek.common.model.Article;

import java.util.List;
/**
 * @author AZhao
 */
public interface HomeService {
    List<Article> getArticleList(int startNum, int pageSize, String categoryName);

    List<Article> getHotArticleList();
}

