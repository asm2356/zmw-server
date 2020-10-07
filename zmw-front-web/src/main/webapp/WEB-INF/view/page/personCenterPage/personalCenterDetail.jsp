<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
            width: 1100px;
            margin: 10px auto 0;
        }

        .wall {
            width: 99.9%;
            height: 300px;
            overflow: hidden;
        }

        .wall img {
            width: 100%;
            height: 100%;
        }

        .person-center-body {
            margin-top: -50px;
            margin-left: 30px;
        }

        .person-header {
            width: 200px;
            height: 200px;
            border-radius: 20px;
        }

        .person-header:before {
            content: "";
            position: absolute;
            width: 200px;
            height: 200px;
            background-color: black;
            border-radius: 20px;
            opacity: 0;
            -webkit-transition: all 500ms linear 0s;
            -moz-transition: all 500ms linear 0s;
            -ms-transition: all 500ms linear 0s;
            -o-transition: all 500ms linear 0s;
            transition: all 500ms linear 0s;
        }

        .base-header {
            width: 200px;
            height: 200px;
            border-radius: 20px;
        }

        .person-header:hover:before {
            opacity: 0.6;
        }

        .person-header-icon {
            position: absolute;
            top: 120px;
            left: 40px;
            opacity: 0;
            cursor: pointer;
            -webkit-transition: all 300ms linear 0s;
            -moz-transition: all 300ms linear 0s;
            -ms-transition: all 300ms linear 0s;
            -o-transition: all 300ms linear 0s;
            transition: all 300ms linear 0s;
        }

        .person-header:hover .person-header-icon {
            top: 95px;
            left: 50px;
            opacity: 1;
        }

        .person-header i {
            font-size: 25px;
        }

        .person-header-tips {
            position: absolute;
            top: 70px;
            left: 70px;
            opacity: 0;
            color: white;
            -webkit-transition: all 200ms linear 0s;
            -moz-transition: all 200ms linear 0s;
            -ms-transition: all 200ms linear 0s;
            -o-transition: all 200ms linear 0s;
            transition: all 200ms linear 0s;
        }

        .person-header:hover .person-header-tips {
            position: absolute;
            top: 95px;
            left: 70px;
            opacity: 1;
        }

        input[type='file'] {
            opacity: 0;
            position: absolute;
        }
    </style>

</head>
<body>
<div class="header">
    <jsp:include page="../../public/header.jsp"/>
</div>
<div class="main-body layui-card">
    <div class="layui-card-body">
        <div class="wall">
            <c:if test="${isCurrentUser}">
                <input type="file" id="wallFile" accept="image/*">
            </c:if>
            <img src="${userBaseInfo.cover}" id="wallImg"/>
        </div>
        <c:if test="${isCurrentUser}">
            <input type="file" id="headerFile" accept="image/*"/>
        </c:if>
        <div class="layui-row person-center-body">
            <c:if test="${isCurrentUser}">
                <div class="layui-col-md3 person-header" id="headerImgDiv">
                    <div class="person-header-icon">
                        <i class="layui-icon layui-icon-camera"></i>
                    </div>
                    <span class="person-header-tips">
                        &nbsp;修改我的头像
                    </span>
                    <div>
                        <img class="base-header" src="${userBaseInfo.header}" id="headerImg"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${!isCurrentUser}">
                <div class="layui-col-md3 base-header">
                    <img class="base-header" src="${userBaseInfo.header}"/>
                </div>
            </c:if>

            <div class="layui-col-md9">
                <div class="person-info">
                    <div style="margin-top: 70px;font-size: 28px">${userBaseInfo.alias}</div>
                    <div style="font-size: 16px">个性签名:${userBaseInfo.introduction}</div>
                    <div style="margin-top: 40px;">
                        <span>文章数:${articleNumber}</span>
                        <span style="margin-left: 10px;">获赞数:${userPraiseNumber}</span>
                    </div>
                </div>
                <c:if test="${!isCurrentUser}">
                    <div style="float: right;margin-top: -25px;margin-right: 8px;">
                        <button class="layui-btn layui-btn-normal" style="width: 100px;"
                                onclick="concerned(getLastPath())">
                            <i class="iconfont icon-jia"></i>关注
                        </button>
                        <button class="layui-btn  layui-btn-primary" onclick="sendLetterToUser(getLastPath())"
                                style="width: 100px;">
                            <i class="iconfont icon-sixin"></i>&nbsp;私信
                        </button>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="layui-tab layui-tab-brief" id="tab" lay-filter="myTypeTab" style="margin-top: 10px;">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="articleTab">文章</li>
                <li>关注的人</li>
                <li>粉丝</li>
                <li>收藏</li>
                <c:if test="${isCurrentUser}">
                    <li>评论</li>
                </c:if>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item">
                    <%--文章 --%>
                    <jsp:include page="articleDetail.jsp"/>
                </div>
                <div class="layui-tab-item ">
                    <%--关注--%>
                    <jsp:include page="concernedDetail.jsp"/>

                </div>
                <div class="layui-tab-item">
                    <jsp:include page="fansDetail.jsp"/>

                </div>
                <div class="layui-tab-item">
                    <jsp:include page="collectDetail.jsp"/>

                </div>
                <div class="layui-tab-item">
                    <jsp:include page="discussDetail.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use('element', function () {
        let element = layui.element;
        element.init();
        element.tabChange('myTypeTab', 'articleTab');
    });

</script>

<script type="text/javascript">

    layui.use(['layer'], function () {
        $("#wallImg").click(function () {
            $("#wallFile").click();
        });
        $("#wallFile").change(function () {
            let formData = new FormData();
            formData.append("coverImg", $("#wallFile")[0].files[0]);
            let url = $("#wallImg").attr("src");
            formData.append("oldImg", url);
            $.ajax({
                url: '${pageContext.request.contextPath}/changeCover.do',
                type: 'post',
                data: formData,
                contentType: false,
                processData: false,
                success: function (res) {
                    $("#wallImg").attr("src", res.data);
                }
            })
        });
        $("#headerImgDiv").click(function () {
            $("#headerFile").click();
        });
        $("#headerFile").change(function () {
            let formData = new FormData();
            formData.append("headerImg", $("#headerFile")[0].files[0]);
            formData.append("oldImg", $("#headerImg").attr("src"));
            $.ajax({
                url: '${pageContext.request.contextPath}/changeHeader.do',
                type: 'post',
                data: formData,
                contentType: false,
                processData: false,
                success: function (result) {
                    if (result.code == 1) {
                        $("#headerImg").attr("src", result.data);
                    } else {
                        layer.msg(result.message, {icon: 5, offset: "100px"})
                    }
                }
            });
        });
    });
</script>
<script>

</script>
</body>
</html>
