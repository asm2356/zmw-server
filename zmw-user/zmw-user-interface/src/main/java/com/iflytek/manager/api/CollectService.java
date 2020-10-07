package com.iflytek.manager.api;

import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.Article;

import java.util.List;

/**
 * @author AZhao
 */
public interface CollectService {
    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取收藏文章的类表
     */
    List<Article> getCollectArticleList(String mwId, long startNum, int pageSize);


    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 某个人收藏某个作品
     */
    int collectArticle(String mwId, String articleId) throws DBException;

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 取消文章收藏
     */
    int cancelCollectArticle(String mwId, String articleId);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取收藏的文章的数量
     */
    int getCollectArticleNumber(String mwId);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 删除某一篇文章相关的收藏者
     */
    int deleteCollectByArticleId(String articleId);
}
