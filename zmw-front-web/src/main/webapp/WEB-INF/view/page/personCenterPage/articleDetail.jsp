<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .title-picture {
        width: 180px;
        height: 120px;
        border-radius: 5px;
    }

    .article-title {
        margin-top: 10px;
        font-weight: 600;
        color: #242424;
        cursor: pointer;
    }

    .article-title > span {
        font-size: 20px;
    }

    .article-title span:hover {
        color: dodgerblue;
    }

    .article-category {
        margin-top: 50px;
    }

    .article-release-time {
        margin-left: 10px;
        float: right;
    }
</style>
<div>
    <c:forEach items="${articleList}" var="article">
        <div class="layui-row">
            <div class="layui-col-md2">
                <img src="${article.titlePicture}" onclick="goToArticle('${article.articleId}')"
                     class="title-picture"/>
            </div>
            <div class="layui-col-md2 article-title" style="margin-left: 30px;">
                <span onclick="goToArticle('${article.articleId}')">${article.title}</span>
                <div class="article-category">${article.categoryName}</div>
            </div>
            <div class="layui-col-md7 layui-row" style="margin-top: 80px;">
                <div class="layui-col-md7">&nbsp;</div>
                <div class="layui-col-md1">
                    <i class="iconfont icon-z-like"></i>
                    <span>${article.praiseNumber}</span>
                </div>
                <div class="layui-col-md1">
                    <i class="iconfont icon-z-like icon-ico_yueduliang"
                       style="font-size: 18px;margin-left: 10px;"></i>
                    <span>${article.readingNumber}</span>
                </div>
                <div class="layui-col-md3">
                    <i class="iconfont icon-pinglun" style="font-size: 18px;margin-left: 10px;"></i>
                    <span>${article.commentNumber}</span>
                    <jsp:useBean id="dateValue" class="java.util.Date"/>
                    <jsp:setProperty name="dateValue" property="time" value="${article.releaseTime}"/>
                    <span class="article-release-time">
                                    <fmt:formatDate type="date" value="${dateValue}" pattern="yyyy-MM-dd"/>
                                    </span>
                    <c:if test="${isCurrentUser}">
                        <div style="position: absolute;top: -70px;left: 100px;">
                            <i class="iconfont icon-shanchu" style="cursor: pointer"
                               onclick="deleteArticle('${article.articleId}')"></i>
                            <i class="iconfont icon-gongkai" style="cursor: pointer"
                               onclick="setOpenness('${article.articleId}')"></i>
                        </div>
                    </c:if>
                </div>

            </div>
        </div>
        <hr>
    </c:forEach>
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px;display: none"
          id="opennessModel">
        <div class="layui-form-item">
            <div style="margin-left: 50px">
                <div>
                    <input type="radio" name="openness" value="1" title="公开" checked="checked"
                           lay-filter="openness">
                </div>
                <div>
                    <input type="radio" name="openness" value="2" title="私密"
                           lay-filter="openness">
                </div>
                <div>
                    <input type="radio" name="openness" value="3" title="加密"
                           lay-filter="openness">
                    <input type="password" id="pwd" autocomplete="off" placeholder="请输入文章密码"
                           style="width: 200px;display: none" class="layui-input">
                </div>

            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" style="margin-top: 10px;" lay-submit id="opennessChange">立即修改</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    function deleteArticle(articleId) {
        layui.use(['layer'], function () {
            let layer = layui.layer;
            layer.confirm('您真的要删除这篇文章吗', {icon: 7, title: '提示', offset: '100px'}, function (index) {
                $.post("/deleteArticle.do", {articleId: articleId}, function (result) {
                    if (result.code == 1) {
                        layer.msg("删除成功", {icon: 1, offset: '100px'});
                        window.location.reload();
                    } else {
                        layer.msg(result.message, {icon: 5, offset: '100px'});
                    }
                });
                layer.close(index);
            });

        });
    }

    function setOpenness(articleId) {
        layui.use(['layer', 'form'], function () {
            let form = layui.form;
            let layer = layui.layer;
            $("#pwdGroup").hide();
            form.on('radio(openness)', function (data) {
                if (data.value == 3) {
                    $("#pwd").show();
                } else {
                    $("#pwd").hide();
                }
            });
            layer.open({
                type: 1,
                title: '公开度设置',
                area: ['300px', '260px'],
                resize: false,
                offset: '100px',
                content: $('#opennessModel')
            });
            $("#opennessChange").click(function () {
                let openness = $("input[name='openness']:checked").val();
                console.log(openness)
                let pwd = trim($("#pwd").val());
                if (openness == 3 && pwd == "") {
                    layer.msg("加密密码不能为空", {icon: 7, offset: '100px'});
                    return;
                }
                $.post("${pageContext.request.contextPath}/changeArticleOpenness.do", {
                    articleId: articleId,
                    openness: openness,
                    pwd: pwd
                }, function (result) {
                    if (result.code == 1) {
                        layer.msg("修改成功", {icon: 1, offset: '100px'});
                    } else {
                        layer.msg(result.message, {icon: 5, offset: '100px'});
                    }
                })
            });
        });
    }
</script>
