package com.iflytek.manager.api;

import com.iflytek.common.exception.DeleteFileException;
import com.iflytek.common.exception.PageException;
import com.iflytek.common.model.Article;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
public interface ArticleService {
    /**
     * @param map enum openness
     *            String categoryName 不为空查询所有类别的文章
     *            String mwId 为空查询所有作者文章
     *            String timeOrder 为空时间不排序，desc降序排序，asc升序排序
     *            Long startNum 开始页码
     *            Integer pageSize 页大小
     *            enum status 文章的发布状态 默认已发布,
     *            int startTime 时间区间查询,
     *            int endTime 时间区间查询,
     *            String title 根据标题进行模糊查询
     *            boolean hot 最热文章 //默认发返回的不是最热文章
     * @return 返回文章列表
     * @author zgzhao
     * @describe 查询文章列表，不包括文章内容
     */
    List<Article> getArticleList(Map<String, Object> map) throws PageException;


    /**
     * @param map enum openness
     *            String categoryName 不为空查询所有类别的文章
     *            String mwId 为空查询所有作者文章
     *            enum status 文章的发布状态 默认已发布,
     *            int startTime 时间区间查询,
     *            int endTime 时间区间查询,
     *            String title 根据标题进行模糊查询
     * @return 返回文章列表
     * @author zgzhao
     * @describe 获取文章数量
     */
    int getArticleNumber(Map<String, Object> map);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 查询某一篇文章信息，包括文章内容
     */
    Article getArticle(String articleId);


    /**
     * @param
     * @return 成功返回文章
     * @author zgzhao
     * @describe 写文章将文章内容保存到数据当中，包括文章内容
     */
    Article writeArticle(Article article);

    /**
     * @param articleId
     * @param isIncrement 是否增加赞
     *                    redis 更新文章赞数
     */
    void updateArticlePraise(String articleId, boolean isIncrement);

    /**
     * @param discussId
     * @param isIncrement 是否增加赞
     *                    redis 更新 评论 赞数
     */
    void updateDiscussPraise(String discussId, boolean isIncrement);

    /**
     * @param replyId
     * @param isIncrement 是否增加赞
     *                    redis 更新 回复 评论 赞数
     */
    void updateReplyPraise(String replyId, boolean isIncrement);

    /**
     * @param articleId
     * @param increment
     * @author zgzhao
     * @describe 数据库 更新文章赞数
     */
    void updatePraise(String articleId, int increment);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe redis 缓存 更新文章阅读量
     */
    void updateReadingNumber(String articleId);

    /**
     * @param articleId
     * @param increment 新增的阅读量
     * @author zgzhao
     * @describe 数据库更新 文章阅读量
     */

    void updateReadingNumber(String articleId, int increment);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取用户总阅读量
     */
    int getReadingNumber(String mwId);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 删除文章
     */
    int deleteArticle(String articleId) throws DeleteFileException;

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 修改文章的公开度
     */
    void updateOpenness(String articleId, int openness, String pwd);
}
