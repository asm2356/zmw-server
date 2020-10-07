<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关于</title>
    <style>
        .container {
            background-image: url('/static/img/menghuan.jpg');
            filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
            -moz-background-size: 100% 100%;
            background-size: 100% 100%;
            height: 100vh;
        }

        .header {
            width: 100%;
            text-align: right;
            background-color: white;
        }

        .menu {
            margin-right: 200px;
            padding: 12px;
            font-size: 18px;
        }

        .menu span {
            margin-left: 15px;
        }

        .menu span:nth-child(3) {
            color: blue;
        }

        .menu span:hover {
            color: #090805;
            cursor: pointer;
        }

        .main-body {
            width: 800px;
            height: 500px;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            margin: 30px auto 0;
        }

        .main-body .title {
            position: relative;
            left: 0;
            right: 0;
            font-size: 30px;
            text-align: center;
        }

        .introduce {
            margin-top: 50px;
        }

        .introduce-title {
            font-size: 15px;
            color: black!important;

        }

        .introduce-content {
            font-size: 13px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/layui/css/layui.css">
</head>
<body>
<div class="container">
    <div class="header">
        <div class="menu">
            <span onclick="window.location.href='/'">首页</span>
            <span onclick="window.location.href='/home'">发现</span>
            <span onclick="window.location.href='/about'">关于</span>
            <span onclick="window.location.href='/login'">登陆</span>
        </div>
    </div>
    <div class="main-body">
        <div class="title">关于项目</div>
        <div class="introduce">
            <ul class="layui-timeline">
                <li class="layui-timeline-item">
                    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                    <div class="layui-timeline-content layui-text ">
                        <p class="layui-timeline-title introduce-title">作者赠语</p>
                        <div class=" introduce-content">
                            <div>人生很短，江湖太大，目标还在，让我们在成长路上彼此祝福。</div>
                            <div>其实我们每个人的生活都是一个世界， 即使最平凡的人也要为他生活的那个世界努力。</div>
                            <div>你还年轻，别凑活过；接下来的人生，还有万万种可能。</div>
                        </div>
                    </div>

                </li>
                <li class="layui-timeline-item">
                    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                    <div class="layui-timeline-content layui-text ">
                        <p class="layui-timeline-title introduce-title">项目介绍</p>
                        <div class="introduce-content">
                            页面参考美篇(https://www.meipian.cn/),实现了前后端分离,Session共享，可以将应用部署到多个服务器运行
                            <div>使用技术框架:</div>
                            <div style="margin-left: 10px;">前端:Vue,JSP,layui,iview</div>
                            <div style="margin-left: 10px;">
                                后端:Spring,SpringMVC,Mybatis,Mybatis-plus,Mysql,Redis,Elasticsearch,Xxl-job,ActiveMq,FastDFS,Zookeeper,Dubbo,Swagger2
                            </div>
                        </div>
                    </div>
                </li>
                <li class="layui-timeline-item">
                    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                    <div class="layui-timeline-content layui-text ">
                        <p class="layui-timeline-title introduce-title">项目结构</p>
                        <div class="introduce-content">
                            搜索模块，配置中心,前台页面,后台页面,用户模块
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
</div>
</body>
</html>
