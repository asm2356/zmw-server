<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>美文</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/swiper/swiper.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/animate.min.css">
    <script src="${pageContext.request.contextPath}/static/assets/swiper/swiper.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/swiper/swiper.animate.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/logo.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/layui/css/layui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/layui.js"></script>
    <style type="text/css">
        .menu {
            float: right;
            font-size: 18px;
            margin-right: 200px;
            padding: 12px;
        }

        .menu a {
            margin-left: 15px;
            text-decoration: none;
            cursor: pointer;
        }

        .menu a:link {
            color: #0000FF;
        }

        .menu a:first-child {
            color: white;
        }

        .menu a:hover {
            cursor: pointer;
        }

        .logo-head {
            float: left;
            margin-left: 250px;
            font-size: 26px;
            z-index: 777;
            padding: 8px;
            font-family: Cambria, serif;
            font-style: italic;
            color: white;
        }

        .page1 {
            background-image: url('${pageContext.request.contextPath}/static/img/page1.jpg');
            filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
            -moz-background-size: 100% 100%;
            background-size: 100% 100%;
            height: 100vh;
        }

        .page3-left {
            font-size: 18px;
            display: inline-block;
            margin-top: 100px;
            color: white
        }

        .page4 {
            margin-top: 40px;
        }

        .page4 img {
            width: 100%;
        }

        .page4 img + div {
            text-align: center;
            margin-top: 5px;
        }

        .page5 {
            background-image: url("${pageContext.request.contextPath}/static/img/yuhui.jpg");
            filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
            -moz-background-size: 100% 100%;
            background-size: 100% 100%;
            height:60%;
        }

        .page5-title {
            color: white;
            font-size: 38px;
            text-align: center;
        }

        .page5-content {
            position: relative;
            top: 30%;
        }

        .page5-btn {
            margin: 0 auto;
            width: 400px;
            font-size: 38px;
            text-align: center;
            cursor: pointer;
            color: white;
            border-radius: 25px;
            -webkit-transition: all 1s;
            -moz-transition: all 1s;
            -ms-transition: all 1s;
            -o-transition: all 1s;
            transition: all 1s;
        }

        .page5-btn:hover {
            color: wheat;
        }
    </style>
</head>
<%--https://www.swiper.com.cn/--%>
<body>
<div class="swiper-container">
    <div class="swiper-wrapper">
        <section class="swiper-slide swiper-slide page1">
            <div>
                <div class="logo-head">
                    MW
                </div>
                <div class="menu">
                    <a href="${pageContext.request.contextPath}/">首页</a>
                    <a href="${pageContext.request.contextPath}/home">发现</a>
                    <a href="${pageContext.request.contextPath}/about">关于</a>
                    <a href="${pageContext.request.contextPath}/login">登录</a>
                </div>
            </div>
            <div class="ys ani resize" style="top:500px;position: absolute; " swiper-animate-effect="zoomIn"
                 swiper-animate-duration="1.0s"
                 swiper-animate-delay="0s">
                <div style="text-align: center">
                    <div class="page1-content">
                        <div class="title ">美文分享与创作</div>
                        <div class="describe ">
                            <p>朋友圈9张图，微博140个字</p>
                            <p>你是不是已经习惯这样克制的表达？</p>
                            <p>你创作的思路不该受到束缚，所以美篇来了</p>
                            <p>分享生活，感悟人生</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="swiper-slide swiper-slide page2">
            <div style="text-align: center;">
                <div>
                    <img src="${pageContext.request.contextPath}/static/img/happy.jpg"
                         style="width: 400px;height:200px;vertical-align: middle;"/>
                    <div class="ani" swiper-animate-effect="rotateInDownRight"
                         swiper-animate-duration="0.5s" swiper-animate-delay="0s"
                         style="font-size: 18px;display:inline-block;vertical-align: middle;width: 400px;
                          color: black;margin-left: 30px;">
                        每天分享一篇文章
                    </div>
                </div>
            </div>
            <div class="layui-row" style="background-color: #1c96ff;">
                <div class="layui-col-md6 " style="text-align: center;">
                    <div class="ani page3-left" swiper-animate-effect="bounceInUp"
                         swiper-animate-duration="1.0s" swiper-animate-delay="0s">
                        <div style="font-size: 30px;">无所不能</div>
                        <div style="margin-top: 10px;">
                            <p>除了文章</p>
                            <p>也可发图片</p>
                            <p>还可放背景音乐和视频</p>
                        </div>
                    </div>
                </div>
                <img class="layui-col-md6" src="${pageContext.request.contextPath}/static/img/diannao.jpg"
                     style="vertical-align: middle;"/>
            </div>
        </section>

        <section class="swiper-slide swiper-slide">
            <div class="layui-row page4">
                <div class="layui-col-md2">&nbsp;</div>
                <div class="layui-col-md2 ani" swiper-animate-effect="fadeInLeft"
                     swiper-animate-duration="1.0s" swiper-animate-delay="0s">
                    <img src="${pageContext.request.contextPath}/static/img/meinv2.jpg">
                    <div>可以进行点赞</div>
                </div>
                <div class="layui-col-md1">&nbsp;</div>
                <div class="layui-col-md2 ani" swiper-animate-effect="fadeInDown"
                     swiper-animate-duration="1.0s" swiper-animate-delay="0s">
                    <img src="${pageContext.request.contextPath}/static/img/shunv.jpg"/>
                    <div>可以评论文章</div>
                </div>
                <div class="layui-col-md1">&nbsp;</div>
                <div class="layui-col-md2 ani" swiper-animate-effect="fadeInRight"
                     swiper-animate-duration="1.0s" swiper-animate-delay="0s">
                    <img src="${pageContext.request.contextPath}/static/img/xiangji.jpg"/>
                    <div>还可关注收藏文章</div>
                </div>
                <div class="layui-col-md2">&nbsp;</div>
            </div>
            <div class="layui-row page5">
                <div class="page5-content ani" swiper-animate-effect="slideInLeft"
                     swiper-animate-duration="1.0s" swiper-animate-delay="0s">
                    <div class="page5-title">这世界的精彩，超乎你的想象</div>
                    <div class="page5-btn" onclick="window.location.href='${pageContext.request.contextPath}/home'">开启我们的文学旅行吧</div>
                </div>
            </div>
            <div class="layui-row">
                <div style="text-align: center;margin-top: 5px;">
                    <div>违法和不良信息举报：011-123456</div>
                    <div>版权声明：您应尊重相关作品著作权人合法权益，未经著作权人合法授权，不能违法上传、存储、分享他人作品</div>
                    <div>Copyright © 2018.Company name All rights reserved.</div>
                </div>
            </div>
        </section>
    </div>
    <div class="swiper-pagination"></div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/logo.js"></script>
</body>
</html>
