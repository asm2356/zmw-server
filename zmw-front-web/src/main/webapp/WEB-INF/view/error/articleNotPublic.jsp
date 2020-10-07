<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章没公开呀</title>
    <style>
        .container {
            width: 500px;
            height: 300px;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            background-color: white;
        }

        .right-img {
            width: 240px;
            height: 300px;
            float: right;
            padding: 10px;
            position: relative;
            left: 10px;
        }


        .left-content {
            float: left;
            width: 220px;
        }
        .left-content>h1{
            margin-left: 40px;
            margin-top: 35px;
            color: rgba(19, 19, 19, 0.96);
        }
        .explain{
            font-size: 14px;
            margin-left: 40px;
            margin-top: 20px;
            line-height: 25px;
        }
        .go-to-index{
            margin-left: 40px;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<jsp:include page="../public/public.jsp"/>
<div>
    <div class="container">
        <div class="left-content">
            <h1>蓝瘦啊!</h1>
            <div class="explain">您访问的文章作者不对外公开</div>
            <button onclick="window.location.href='${pageContext.request.contextPath}/home'"
                    class="go-to-index layui-btn">返回首页
            </button>
        </div>
        <img src="${pageContext.request.contextPath}/static/img/nanshou.jpg" class="right-img"/>
    </div>
</div>
<script>
</script>
</body>
</html>
