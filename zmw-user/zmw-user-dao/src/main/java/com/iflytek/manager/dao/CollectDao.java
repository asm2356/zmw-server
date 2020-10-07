package com.iflytek.manager.dao;

import com.iflytek.common.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Repository
public interface CollectDao {
    List<Article> getCollectArticleList(Map<String, Object> map);
    /**
     * mwId,
     * articleId
     *
     * @param map
     * @return
     */
    int addCollectArticle(Map<String, Object> map);

    int deleteCollectArticle(Map<String, Object> map);

    int getCollectArticleNumber(String mwId);


    int deleteCollectByArticleId(String articleId);
}
