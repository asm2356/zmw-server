<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .collect-title-picture {
        width: 180px;
        height: 120px;
        border-radius: 5px;
    }

    .collect-article-title {
        margin-top: 10px;
        font-weight: 600;
        color: #242424;
        font-size: 20px;
        cursor: pointer;
    }

    .collect-article-title:hover {
        color: dodgerblue;
    }

    .collect-author {
        display: inline;
        margin-left: 5px;
    }

    .collect-author:hover {
        color: dodgerblue;
    }

    .collect-switch-btn {
        float: right;
        margin-top: 25px;
        margin-right: 20px;
    }

    .collect-user-header {
        width: 20px;
        height: 20px;
        border-radius: 50%;
        float: left;
    }
</style>
<div>
    <c:forEach items="${collectList}" var="collectArticle">
        <div class="layui-row">
            <div class="layui-col-md2">
                <img class="collect-title-picture"
                     onclick="goToArticle('${collectArticle.articleId}')"
                     src="${collectArticle.titlePicture}"/>
            </div>
            <div class="layui-col-md8" style="margin-left: 30px;">
                <div class="collect-article-title"
                     onclick="goToArticle('${collectArticle.articleId}')">${collectArticle.title}</div>
                <div style="margin-top: 50px;">
                    <img src="${collectArticle.userBaseInfo.header}"
                         class="collect-user-header"
                         onclick="goToUser('${collectArticle.userBaseInfo.mwId}')"/>
                    <div class="collect-author">
                        <span>${collectArticle.userBaseInfo.alias}</span>
                        <span style="margin-left: 10px;">
                              <i class="iconfont icon-z-like"></i>${collectArticle.praiseNumber}
                        </span>&nbsp;&nbsp;
                        <span>
                                            <i class="iconfont icon-ico_yueduliang"></i>${collectArticle.readingNumber}</span>&nbsp;&nbsp;
                        <span><i
                                class="iconfont icon-pinglun"></i>${collectArticle.commentNumber}</span>
                    </div>
                </div>
            </div>
            <div class="layui-col-md1">
                <button class="collect-switch-btn layui-btn layui-btn-primary"
                        onclick="cancelArticleCollect('${collectArticle.articleId}')">
                    <i class="iconfont icon-shoucang-tianchong" style="margin-right: 10px;"></i>取消收藏
                </button>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>
<script type="text/javascript">
    function cancelArticleCollect(articleId) {
        $.post("${pageContext.request.contextPath}/collectArticle.do", {
            articleId: articleId,
            isCollect: false
        }, function (result) {
            console.log("文章取消收藏成功");
            window.location.reload();
        })
    }
</script>
