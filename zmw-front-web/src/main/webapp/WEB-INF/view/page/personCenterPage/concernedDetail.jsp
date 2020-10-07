<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .noticer-header {
        width: 100px;
        height: 100px;
        border-radius: 15px;
        padding: 10px;
    }

    .noticer-alias {
        font-size: 18px;
        text-decoration: none;
        cursor: pointer;
        font-weight: 600;
        color: #2c2c2c;
    }

    .noticer-alias:hover {
        color: dodgerblue;
    }

    .noticer-introduction {
        margin-top: 8px;
    }

    .noticer-concerned {
        float: right;
        margin-top: 25px;
        margin-right: 20px;
    }
</style>
<div>
    <c:forEach items="${noticerList}" var="user">
        <div class="layui-row" onclick="goToUser('${user.mwId}')">
            <div class="layui-col-md2">
                <img src="${user.header}" class="noticer-header"/>
            </div>
            <div class="layui-col-md10" style="margin-top: 10px;margin-left: -50px;">
                <div style="float: left">
                    <div class="noticer-alias">${user.alias}</div>
                    <div class="noticer-introduction">
                            ${user.introduction}
                    </div>
                </div>
                <button class="noticer-concerned layui-btn layui-btn-primary"
                        onclick="cancelConcerned('${user.mwId}')">
                    <i class="iconfont icon-jia"></i>取消关注
                </button>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>
