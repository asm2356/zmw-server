<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .fans-header {
        width: 100px;
        height: 100px;
        border-radius: 15px;
        padding: 10px;
    }

    .fans-alias {
        font-size: 18px;
        text-decoration: none;
        cursor: pointer;
        font-weight: 600;
        color: #2c2c2c;
    }

    .fans-alias:hover {
        color: dodgerblue;
    }

    .fans-introduction {
        margin-top: 8px;
    }

    .fans-concerned {
        float: right;
        margin-top: 25px;
        margin-right: 20px;
    }
</style>
<div>
    <c:forEach items="${fansList}" var="fans">
        <div class="layui-row" onclick="goToUser('${fans.mwId}')">
            <div class="layui-col-md2">
                <img src="${fans.header}" class="fans-header"/>
            </div>
            <div class="layui-col-md10" style="margin-top: 10px;margin-left: -50px;">
                <div style="float: left">
                    <div class="fans-alias">${fans.alias}</div>
                    <div class="fans-introduction">
                            ${fans.introduction}
                    </div>
                </div>
                <button class="fans-concerned layui-btn layui-btn-primary"
                        onclick="concerned('${fans.mwId}')">
                    <i class="iconfont icon-jia"></i>相互关注
                </button>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>