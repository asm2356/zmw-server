package com.iflytek.front.web.service.imp;

import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.common.model.Article;
import com.iflytek.front.web.service.HomeService;
import com.iflytek.manager.api.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author AZhao
 */
@Service
public class HomeServiceImp implements HomeService {
    @Autowired
    private ArticleService articleService;
    public List<Article> getArticleList(int startNum, int pageSize, String categoryName) {
        Map<String, Object> articleMap = new HashMap<>();
        articleMap.put("startNum", startNum);
        articleMap.put("pageSize", pageSize);
        articleMap.put("openness", Openness.Open.getValue());
        if (categoryName != null && !categoryName.equals("")) {
            articleMap.put("categoryName", categoryName);
        }
        articleMap.put("hot", false);
        return articleService.getArticleList(articleMap);
    }

    @Cacheable(value = "getHotArticleList")
    public List<Article> getHotArticleList() {
        Map<String, Object> articleMap = new HashMap<>();
        articleMap.put("startNum", 0);
        articleMap.put("pageSize", 6);
        articleMap.put("openness", Openness.Open.getValue());
        articleMap.put("hot", true);
        return articleService.getArticleList(articleMap);
    }
}
