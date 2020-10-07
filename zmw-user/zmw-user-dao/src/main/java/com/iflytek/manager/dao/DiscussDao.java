package com.iflytek.manager.dao;

import com.iflytek.common.model.Discuss;
import com.iflytek.common.model.ReplyDiscuss;
import com.iflytek.common.model.dto.UserArticleDiscuss;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Repository
public interface DiscussDao {
    int getDiscussArticleCount(String articleId);

    List<Discuss> getDiscussList(@Param("articleId") String articleId, @Param("startNum") long startNum, @Param("pageSize") int pageSize);

    List<Discuss> getOnlyDiscussList(Map<String, Object> map);

    int getOnlyDiscussCount(Map<String, Object> map);

    int insertDiscuss(Discuss discuss);

    int insertReplyDiscuss(ReplyDiscuss replyDiscuss);

    int deleteDiscuss(String discussId);

    int deleteReplyDiscuss(String replyDiscussId);

    int updateDiscussPraise(@Param("discussId") String discussId, @Param("praiseNumber") int praiseNumber);

    int updateReplyDiscussPraise(@Param("replyDiscussId") String replyDiscussId, @Param("praiseNumber") int praiseNumber);

    List<UserArticleDiscuss> getUserArticleDiscussList(@Param("mwId") String mwId,
                                                       @Param("startNum") int startNum,
                                                       @Param("pageSize") int pageSize);


    int deleteDiscussByArticleId(String articleId);

    int deleteReplyDiscussArticleById(String articleId);

    List<ReplyDiscuss> getReplyDiscussList(Map<String, Object> map);

    int getReplyDiscussCount(Map<String, Object> map);
}
