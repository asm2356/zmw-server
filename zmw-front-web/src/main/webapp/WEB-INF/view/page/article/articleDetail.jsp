<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>文章</title>
    <style type="text/css">
        body {
            background-color: #F6F6F6;
        }

        .header {
            background-color: white;
            width: 100%;
            height: 50px;
        }

        .main-body {
            width: 1200px;
            margin: 10px auto 0;
        }

        .author-header {
            width: 100px;
            height: 100px;
            border-radius: 50%;

        }

        .author-name {
            margin-top: 15px;
            font-size: 20px;
            font-weight: bold;
            color: black;
            cursor: pointer;
        }

        .author-name:hover {
            color: dodgerblue;
        }

        .author-article-title {
            margin-top: 5px;
            font-weight: 600;
            color: #2e2e2e;
            display: inline-block;
            height: 40px;
            cursor: pointer;
            margin-left: 10px;
            transition: all 0.5s;
            -moz-transition: all 0.5s;
            -webkit-transition: all 0.5s;
            -o-transition: all 0.5s;
        }

        .author-article-title:hover {
            color: dodgerblue;
        }

        .main-body {
            width: 1200px;
            margin: 15px auto 0;
        }

        .author-header {
            width: 100px;
            height: 100px;
            border-radius: 50%;

        }

        .author-name {
            margin-top: 15px;
            font-size: 20px;
            font-weight: bold;
            color: black;
            cursor: pointer;
        }

        .author-name:hover {
            color: dodgerblue;
        }

        .article-title {
            font-size: 18px;
            margin-left: 20px;
            padding-top: 10px;
            word-wrap: break-word;
        }

        .content {
            padding: 20px;
            word-wrap: break-word;
        }

        .share {
            float: left;
            position: relative;
            left: 20px;
        }

        .praise {
            width: 120px;
            margin: 0 auto;
            position: relative;
            top: -20px;
        }

        .praise-number {
            position: relative;
            top: -60px;
            left: 70px;
        }

        .collector {
            float: right;
            position: relative;
            cursor: pointer;
            padding: 5px 20px;
            right: 25px;
            top: 10px;
        }

        .collector:hover {
            color: dodgerblue;
            border-color: dodgerblue;
        }

        .collector span {
            margin-left: 10px;
            font-size: 16px;
        }

        .article {
            background-color: white;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        .article-bottom {
            background-color: white;
        }

        .create-time {
            margin-top: 5px;
            position: relative;
            padding-top: 5px;
            left: 650px;
            font-size: 12px;

        }

        .article-footer {
            margin-top: 20px;
            height: 70px;
        }

        .input-tips span {
            color: orange
        }

        .title-picture {
            width: 100px;
            height: 70px;
        }

        .heart {
            width: 100px;
            height: 100px;
            background: url("/static/img/heart.png") no-repeat 0 0;
            cursor: pointer;
            -webkit-transition: background-position 1s steps(28);
            transition: background-position 1s steps(28);
            -webkit-transition-duration: 0s;
            transition-duration: 0s;
        }

        .isActive {
            -webkit-transition-duration: 1s;
            transition-duration: 1s;
            background-position: -2800px 0;
        }

        .isCollect {
            color: orange !important;
        }
    </style>
</head>
<body>
<div class="layui-row header">
    <jsp:include page="../../public/header.jsp"/>
</div>
<div class="main-body layui-row">
    <div class="layui-col-md8">
        <div class="article">
            <div class="article-title">
                ${article.title}
            </div>
            <div class="article-title">
                <c:if test="${article.music!=null}">
                    <div style="height: 50px;display: inline-block;" id="musicAudio"></div>
                    <link rel="stylesheet" type="text/css"
                          href="${pageContext.request.contextPath}/static/assets/wx-audio/wx-audio.css">
                    <script type="text/javascript"
                            src="${pageContext.request.contextPath}/static/assets/wx-audio/wx-audio.js"></script>
                    <script>
                        new WxAudio({
                            ele: '#musicAudio',
                            title: '',
                            disc: '',
                            src: '${article.music}',
                            width: '500px',
                            ended: function () {
                            }
                        });
                    </script>
                </c:if>
            </div>
            <div class="content">
                ${article.content}
            </div>
        </div>
        <%--分享 收藏 时间--%>
        <div style="overflow: hidden">
            <div class="article-bottom">
                <!--创建时间-->
                <div class="create-time">
                    <i class="iconfont icon-shijian" style="font-size: 12px;"></i>
                    <jsp:useBean id="dateValue" class="java.util.Date"/>
                    <jsp:setProperty name="dateValue" property="time" value="${article.releaseTime}"/>
                    <span>创建于: <fmt:formatDate type="date" value="${dateValue}" pattern="yyyy-MM-dd"/></span>
                </div>
                <div class="article-footer">
                    <!--分享样式-->
                    <div class="share">
                        <i class="iconfont icon-weibo" style="font-size: 40px;"
                           onclick="share('weibo','${article.title}')"></i>
                        <i class="iconfont icon-QQ" style="font-size: 35px"
                           onclick="share('qq','${article.title}')"></i>
                        <i class="iconfont icon-kongjian" style="font-size: 33px;"
                           onclick="share('kongjian','${article.title}')"></i>
                    </div>
                    <!--收藏样式-->
                    <div class="collector">
                        <%--icon-star__easyico--%>
                        <i class="iconfont icon-shoucang1"></i>
                        收藏
                    </div>
                    <!--点赞样式-->
                    <div class="praise">
                        <div class="heart" id="praise"></div>
                        <div class="praise-number">
                            ${article.praiseNumber}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--评论给--%>
        <div style="margin-top: 5px;">
            <jsp:include page="discuss.jsp"/>
        </div>
    </div>
    <div class="layui-col-md3" style="margin-left: 10px;">
        <%--关于作者--%>
        <div class="layui-card">
            <div class="layui-card-header">关于作者</div>
            <div class="layui-card-body">
                <ul class="layui-row">
                    <li class="layui-col-md5">
                        <img src="${article.userBaseInfo.header}" onclick="goToUser('${article.userBaseInfo.mwId}')"
                             class="author-header ">
                    </li>
                    <li class="layui-col-md7">
                        <div class="author-name"
                             onclick="goToUser('${article.userBaseInfo.mwId}')">${article.userBaseInfo.alias}</div>
                        <div>${article.userBaseInfo.introduction}</div>
                    </li>
                </ul>
                <ul class="layui-row" style="text-align: center">
                    <li class="layui-col-md4">
                        <span>文章 ${articleNumber}</span>
                    </li>
                    <li class="layui-col-md4">
                        <span>浏览量 ${readingNumber}</span>
                    </li>
                    <li class="layui-col-md4">
                        <span>粉丝 ${fansNumber}</span>
                    </li>
                </ul>
                <ul class="layui-row" style="text-align: center">
                    <li class="layui-col-md6">
                        <button class="layui-btn layui-btn-normal" style="width: 120px;"
                                onclick="concerned('${article.userBaseInfo.mwId}')">
                            <i class="iconfont icon-jia"></i>关注
                        </button>
                    </li>
                    <li class="layui-col-md6">
                        <button class="layui-btn layui-btn-primary" style="width: 120px;"
                                onclick="sendLetterToUser('${article.userBaseInfo.mwId}')">
                            <i class="iconfont icon-sixin"></i>&nbsp;私信
                        </button>
                    </li>
                </ul>
            </div>
        </div>
        <%--作者其他文章--%>
        <div class="layui-card">
            <div class="layui-card-header">作者其他文章</div>
            <c:forEach items="${otherArticleList}" var="otherArticle">
                <c:if test="${article.articleId!=otherArticle}">
                    <div class="layui-card-body layui-row " style="padding: 5px 10px!important;">
                        <ul class="layui-col-md5">
                            <img src="${otherArticle.titlePicture}" onclick="goToArticle('${otherArticle.articleId}')"
                                 class="title-picture"/>
                        </ul>
                        <div class="layui-col-md7" style="position: relative;left: -15px;">
                            <div class="author-article-title"
                                 onclick="goToArticle('${otherArticle.articleId}')">
                                <c:if test="${fn:length(otherArticle.title)>8}">
                                    ${fn:substring(otherArticle.title,0 , 8)}...
                                </c:if>
                                <c:if test="${fn:length(otherArticle.title)<=8}">
                                    ${otherArticle.title}
                                </c:if>
                            </div>
                            <ul class="layui-row" style="text-align: center">
                                <li class="layui-col-md4"><i
                                        class="iconfont icon-z-like"></i>${otherArticle.praiseNumber}</li>
                                <li class="layui-col-md4"><i
                                        class="iconfont icon-ico_yueduliang">${otherArticle.readingNumber}</i></li>
                                <li class="layui-col-md4"><i
                                        class="iconfont icon-pinglun"></i>${otherArticle.commentNumber}</li>
                            </ul>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.use('layer', function () {
        let isPraise = true;
        let collect = true;
        $("#praise").click(function () {
            if (isPraise) {
                $("#praise").addClass("isActive");
                $(".praise-number").html(Number.parseInt($(".praise-number").html()) + 1);

            } else {
                $("#praise").removeClass("isActive");
                $(".praise-number").html(Number.parseInt($(".praise-number").html()) - 1);
            }

            $.post('${pageContext.request.contextPath}/updateArticlePraise.do', {
                articleId: '${article.articleId}',
                isIncrement: isPraise
            }, function (result) {
                if (result.code != 1) {
                    layer.msg("亲," + result.message, {icon: 5, offset: '100px'});
                }
            });
            isPraise = !isPraise;
        });
        $(".collector").click(function () {
            if (collect) {
                $(".collector").addClass("isCollect");
            } else {
                $(".collector").removeClass("isCollect");
            }
            $.post('${pageContext.request.contextPath}/articleCollector.do', {
                articleId: '${article.articleId}',
                isCollector: collect
            }, function (result) {
                if (result.code == 1) {
                    layer.msg("收藏成功", {icon: 1, offset: '100px'})
                } else {
                    layer.msg(result.message, {icon: 5, offset: '100px'})
                }
            });
            collect = !collect;
        });
    })
</script>
<script type="text/javascript">
    function share(type, title) {
        let url = "";
        let currentUrl = window.location.href;
        switch (type) {
            case "kongjian":
                url = "https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey"
                break;
            case "weibo":
                url = "http://service.weibo.com/share/mobile.php";
                break;
            case "qq":
                url = "http://connect.qq.com/widget/shareqq/index.html";
                break;
        }
        url = url + "?title=" + title + "&url=" + currentUrl;
        window.open(url)
    }
</script>
</body>
</html>
