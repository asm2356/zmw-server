package com.iflytek.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iflytek.common.model.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Repository
public interface ArticleDao extends BaseMapper<Article> {

    List<Article> getArticleList(Map<String,Object> map);
     void insertArticle(Article article);

    int getArticleNumber(Map<String, Object> map);

    /**
     * @param articleId, praiseNumber
     * @return
     */
    void updatePraise(@Param("articleId") String articleId, @Param("increment") int increment);


    /**
     * @param articleId
     * @param increment
     * @return
     */
    void updateReadingNumber(@Param("articleId") String articleId, @Param("increment") int increment);

    int getReadingNumber(String mwId);


    /**
     * @param map long articleId,
     *            Openness openness
     *            String pwd
     * @return
     * @author zgzhao
     * @describe
     */
    void updateOpenness(Map<String, Object> map);

    Article getArticle(@Param("articleId")String articleId);
}
