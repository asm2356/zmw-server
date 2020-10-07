<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .discuss-user-header {
        width: 20px;
        height: 20px;
        border-radius: 50%;
        float: left;
    }

    .discuss-footer {
        margin-top: 20px;
        padding: 10px;
        background-color: #F5F6F7;
    }

    .discuss-title-picture {
        width: 150px;
        height: 100px;
    }

    .discuss-author {
        display: inline;
        margin-left: 5px;
    }

    .discuss-author:hover {
        color: dodgerblue;
    }

    .discuss-article-title {
        font-weight: 600;
        color: #242424;
        cursor: pointer;
    }

    .discuss-article-title:hover {
        color: dodgerblue;
    }

    .discuss-user-header {
        width: 25px;
        height: 25px;
        vertical-align: middle;
        border-radius: 50%;
        float: right;
        margin-right: 15px;
    }

    .to-user {
        color: black;
        cursor: pointer;
    }

    .to-user:hover {
        color: dodgerblue;
    }
</style>
<div>
    <c:forEach items="${userComment}" var="item">
        <div class="layui-row">
            <div class="layui-col-md1">
                <img src="${item.article.userBaseInfo.header}"
                     class="discuss-user-header"/>
            </div>
            <div class="layui-col-md9">
                <span style="line-height: 30px;">${item.article.userBaseInfo.alias}</span>
                <div>
                        <%--评论文章--%>
                    <c:if test="${item.targetUserBaseInfo==null}">
                        ${item.content}
                    </c:if>
                        <%--评论人 回复主评论人或 相互评论--%>
                    <c:if test="${item.targetUserBaseInfo!=null}">
                        <c:if test="${item.toContent!=null}">
                            ${item.content}
                            <span onclick="goToUser('${item.targetUserBaseInfo.mwId}')"
                                  class="to-user">@ ${item.targetUserBaseInfo.alias}</span>
                            :${item.toContent}
                        </c:if>
                        <c:if test="${item.toContent==null}">
                            ${item.content}
                            <span onclick="goToUser('${item.userBaseInfo.mwId}')"
                                  class="to-user"> @${item.userBaseInfo.alias}</span>
                            :${item.content}
                        </c:if>
                    </c:if>
                </div>
                <div class="discuss-footer layui-row">
                    <div class="layui-col-md3">
                        <img class="discuss-title-picture"
                             onclick="goToArticle('${item.article.articleId}')"
                             src="${item.article.titlePicture}"/>
                    </div>
                    <div class="layui-col-md8" style="margin-left: -30px;">
                        <div class="discuss-article-title"
                             onclick="goToArticle('${item.article.articleId}')">${item.article.title}</div>
                        <div style="margin-top: 50px;">
                            <div class="layui-col-md4">
                                <div style="cursor: pointer">
                                    <img src="${item.article.userBaseInfo.header}"
                                         class="discuss-user-header"/>
                                    <div class="discuss-author"
                                         onclick="goToUser('${item.article.userBaseInfo.mwId}')">${item.article.userBaseInfo.alias}</div>
                                </div>
                            </div>
                            <div class="layui-col-md1 layui-col-md-offset6">
                                <i class="iconfont icon-z-like"></i>
                                <span>${item.article.praiseNumber}</span>
                            </div>
                            <div class="layui-col-md1">
                                <i class="iconfont icon-ico_yueduliang"></i>
                                <span>${item.article.readingNumber}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md1">
                <jsp:useBean id="discussTime" class="java.util.Date"/>
                <jsp:setProperty name="discussTime" property="time"
                                 value="${item.discussTime}"/>
                <span style="float: right">
                      <fmt:formatDate type="date" value="${discussTime}" pattern="yyyy-MM-dd"/>
                </span>
            </div>
        </div>
    </c:forEach>
</div>


