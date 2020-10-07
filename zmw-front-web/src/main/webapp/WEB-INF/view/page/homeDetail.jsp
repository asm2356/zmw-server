<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>发现</title>
    <style type="text/css">
        body {
            background-color: #F6F6F6;
        }

        .header {
            background-color: white;
            width: 100%;
            height: 50px;
        }

        .category {
            width: 100px;
            height: 50px;
            background-color: #F6F6F6;
            text-align: center;
            margin: 10px auto 0;
            line-height: 50px;
            font-size: 16px;
            position: relative;
            border-radius: 9px;
            -webkit-border-radius: 9px;
            -moz-border-radius: 9px;
            transition: 0.8s;
        }

        .category:hover {
            background-color: #2887f0;
            color: white;
            cursor: pointer;
        }

        .main-body {
            width: 1200px;
            margin: 10px auto 0;
        }

        .user-header {
            width: 20px;
            height: 20px;
            border-radius: 50%;
            float: left
        }

        .main-info-card {
            -webkit-transition: all 0.5s;
            -moz-transition: all 0.5s;
            -ms-transition: all 0.5s;
            -o-transition: all 0.5s;
            transition: all 0.5s;
            border: 1px solid transparent;
        }

        .main-info-card:hover {
            border: 1px solid dodgerblue;
        }

        .main-info-content-title {
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            color: #333;
        }

        .main-info-content-title:hover {
            color: dodgerblue;
        }

        .main-info-content-author {
            display: inline;
            margin-left: 5px;
        }

        .main-info-content-author:hover {
            color: dodgerblue;
        }

        .hot-article-title {
            font-size: 18px;
            cursor: pointer;
        }

        .hot-article-title:hover {
            color: dodgerblue;
        }

        .hot-article-author {
            margin-top: 15px;
            cursor: pointer;
            color: #767676;
        }

        .hot-article-author span:hover {
            color: dodgerblue;
        }

        .not-found-article {
            line-height: 300px;
            margin-top: -50px;
            color: black;
            font-size: 25px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/icon/iconfont.css"/>
</head>
<body>
<div class="layui-row header">
    <jsp:include page="../public/header.jsp"/>
</div>
<div class="main-body">
    <div class="layui-row">
        <div class="layui-col-md1" id="categoryList">
            <div class="category" onclick="location.href='${pageContext.request.contextPath}/home'">全部
            </div>
            <div class="category" onclick="location.href='${pageContext.request.contextPath}/home?categoryName=情感'">情感
            </div>
            <div class="category" onclick="location.href='${pageContext.request.contextPath}/home?categoryName=美食'">美食
            </div>
            <div class="category" onclick="location.href='${pageContext.request.contextPath}/home?categoryName=摄影'">摄影
            </div>
            <div class="category" onclick="location.href='${pageContext.request.contextPath}/home?categoryName=随笔'">随笔
            </div>
            <div class="category" onclick="location.href='${pageContext.request.contextPath}/home?categoryName=哲理'">哲理
            </div>
            <div class="category" onclick="location.href='${pageContext.request.contextPath}/home?categoryName=其他'">其他
            </div>
        </div>
        <div>
            <div class="layui-col-md7" style="margin-left: 10px;">&nbsp;
                <c:if test="${articleList.size()==0}">
                    <div class="layui-card">
                        <div class="layui-card-body" style="height: 300px;text-align: center;">
                            <div class="not-found-article">文章飞到火星去啦.....</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${articleList.size()>0}">
                    <c:forEach items="${articleList}" var="article">
                        <div class="layui-card main-info-card" style="margin-bottom: 2px">
                            <div class="layui-card-body layui-row">
                                <div class="layui-col-md3">
                                    <img src="${article.titlePicture}"
                                         onclick="goToArticle('${article.articleId}')"
                                         style="height: 110px;width: 160px"/>
                                </div>
                                <div class="layui-col-md9 layui-row">
                                    <div class="main-info-content-title"
                                         onclick="goToArticle('${article.articleId}')">${article.title}</div>
                                    <div style="margin-top: 50px;" class="layui-row">
                                        <div style="cursor: pointer;" class="layui-col-md3 ">
                                            <img onclick="goToUser('${article.userBaseInfo.mwId}')"
                                                 src="${article.userBaseInfo.header}"
                                                 class="user-header"/>
                                            <div class="main-info-content-author"
                                                 onclick="goToUser('${article.userBaseInfo.mwId}')">${article.userBaseInfo.alias}</div>
                                        </div>

                                        <div class="layui-col-md3">
                                            <i class="iconfont icon-z-like"></i>
                                            <span>${article.praiseNumber}</span>
                                        </div>
                                        <div class="layui-col-md3">
                                            <i class="iconfont icon-ico_yueduliang"></i>
                                            <span>${article.readingNumber}</span>
                                        </div>
                                        <div class="layui-col-md3">
                                            <i class="iconfont icon-pinglun"></i>
                                            <span>${article.commentNumber}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

                <div style="text-align:  center">
                    <div id="page"></div>
                </div>
            </div>

        </div>
        <div class="layui-col-md3 layui-card" style="margin-left: 10px;margin-top:15px; ">
            <div class="layui-card-header">
                最热文章
            </div>
            <div class="layui-card-body">
                <c:forEach items="${hotArticleList}" var="article">
                    <div class="layui-row">
                        <div class="layui-col-md5">
                            <img src="${article.titlePicture}"
                                 onclick="goToArticle('${article.articleId}')"
                                 style="width: 100px">
                        </div>
                        <div class="layui-col-md7">
                            <div class="hot-article-title" onclick="goToArticle('${article.articleId}')">
                                    ${article.title}
                            </div>
                            <div class="hot-article-author ">
                                <span onclick="goToUser('${article.userBaseInfo.mwId}')">${article.userBaseInfo.alias}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
<script>
    let category = getQueryVar("categoryName");
    let currentCategory = "全部";
    if (category) {
        currentCategory = decodeURI(category);
    }
    function homeInit() {
        $("#categoryList div.category").each(function () {
            if (trim($(this).text()) === currentCategory) {
                $(this).css("background-color", "#2887f0");
                $(this).css("color", "#FFFFFF");
            }
        });
    }

    $(function () {
        $("#search").click(function () {
            window.location.href = "/searchHome?title=" + $("#searchContent").val();
        });
        homeInit();
    });
</script>
<script type="text/javascript">
    let startNum = Number.parseInt(getQueryVar("startNum"))
    let pageSize = 10;
    layui.use('laypage', function () {
        let laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
            , count: ${articleCount} //数据总数，从服务端得到
            , groups: 8
            , curr: (startNum + pageSize) / pageSize
            , jump(obj, first) {
                let href = "${pageContext.request.contextPath}/home?startNum=" + (obj.curr - 1) * pageSize + "&pageSize=" + pageSize;
                if (currentCategory !== "全部") {
                    href = href + "&categoryName=" + currentCategory;
                }
                if (!first) {
                    window.location.href = href;
                }
            }
        });
    });
</script>
</html>
