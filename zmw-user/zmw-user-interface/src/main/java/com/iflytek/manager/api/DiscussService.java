package com.iflytek.manager.api;

import com.iflytek.common.model.Discuss;
import com.iflytek.common.model.ReplyDiscuss;
import com.iflytek.common.model.vo.UserComment;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
public interface DiscussService {
    /**
     * @param articleId 文章id
     * @return
     * @author zgzhao
     * @describe 获取某一篇文章评论的条数
     */
    int getDiscussArticleCount(String articleId);

    /**
     * @param articleId 作品Id
     * @param startNum  开始页数
     * @param pageSize  页大小
     * @return 评论 包括回复的评论
     * @describe 获取评论列表，回复者默认显示UserConstant.REPLY_Discuss_PAGE_SIZE 条数
     */
    List<Discuss> getDiscussList(String articleId, long startNum, int pageSize);

    /**
     * @param content   评论内容模糊查询
     * @param mwId      评论者
     * @param articleId 作品Id
     * @param discussId 评论的Id
     * @param startNum  开始页数 必须
     * @param pageSize  页大小 必须
     * @return 评论不包括回复的评论
     */
    List<Discuss> getOnlyDiscussList(Map<String, Object> map);

    /**
     * @param content   评论内容模糊查询
     * @param mwId      评论者
     * @param articleId 作品Id
     * @param discussId 评论的Id
     * @param startNum  开始页数 必须
     * @param pageSize  页大小 必须
     * @param map
     * @return 评论不包括回复的评论
     */
    int getOnlyDiscussCount(Map<String, Object> map);

    void increaseDiscussCount(String articleId);

    void decreaseDiscussCount(String articleId);

    /**
     * @param
     * @return 成功返回主键 失败返回null
     * @author zgzhao
     * @describe 给文章写评论
     */
    String writeDiscuss(Discuss discuss);

    /**
     * @param
     * @return 成功返回主键 失败返回null
     * @author zgzhao
     * @describe 给指定文章的指定用户回复评论
     */
    String writeReplyDiscuss(ReplyDiscuss replyDiscuss);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 删除评论
     */
    int deleteDiscuss(String discussId);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 删除回复的评论
     */
    int deleteReplyDiscuss(String replyDiscussId);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 更新评论的赞
     */
    int updateDiscussPraise(String discussId, int praiseNumber);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 更新回复赞
     */
    int updateReplyDiscussPraise(String replyDiscussId, int praiseNumber);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 查询与某个用户所有评论
     */
    List<UserComment> getUserCommentList(String mwId, int startNum, int pageSize);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取用户评论的数量 包括评论和回复评论的数目
     */
    int getUserDiscussNumber(String mwId);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 删除文章的评论和回复的评论
     */
    int deleteDiscussByArticleId(String articleId);

    /**
     * replyDiscussId id
     * discussId 要回复评论的Id
     * mwId 评论者的MwId
     * toContent 目标内容
     * replyContent 评论内容
     *
     * @param map
     * @return
     */
    List<ReplyDiscuss> getReplyDiscussList(Map<String, Object> map);

    /**
     * replyDiscussId id
     * discussId 要回复评论的Id
     * mwId 评论者的MwId
     * toContent 目标内容
     * replyContent 评论内容
     *
     * @param map
     * @return
     */
    int getReplyDiscussCount(Map<String, Object> map);
}
