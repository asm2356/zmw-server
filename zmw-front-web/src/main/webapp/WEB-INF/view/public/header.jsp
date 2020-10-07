<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    .logo-font {
        font-family: "KaiTi", serif;
        font-size: 24px;
        line-height: 45px;
        text-align: right;
    }

    .label-font {
        font-size: 18px;
        line-height: 50px;
        margin-left: 20px;
        cursor: pointer;
    }

    .account {
        font-size: 23px!important;
        top: 10px;
        position: absolute;
        cursor: pointer;
        left: 40px;
    }

    .header-letter {
        font-size: 25px !important;
        top: 10px;
        position: absolute;
        cursor: pointer;
    }

</style>
<jsp:include page="public.jsp"/>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/static/assets/dropdown/jquery.sweet-dropdown.min.css">
<%--下拉菜单--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/assets/dropdown/jquery.sweet-dropdown.min.js"></script>

<div class="layui-row " style="background-color: white!important;">
    <div class="layui-col-md2">
        <div class="logo-font">
            <i class="iconfont icon-wenzhangzhuantishouye" style="font-size: 28px;"></i> 美文
        </div>
    </div>
    <div class="layui-col-md6">
        <span class="label-font" onclick="window.location.href='/'">首页</span>
        <span class="label-font" onclick="window.location.href='/home'">发现</span>
        <span id="searchService" style="display: none">
            <label>
                <input class="layui-input" id="searchContent"
                       style="display: inline-block;width: 300px;margin-left:20px;"/>
            </label>
            <button class="layui-btn layui-btn-normal" id="search" style="display: inline-block">
                <i class="layui-icon layui-icon-search"></i>搜索
            </button>
        </span>
    </div>
    <div class="layui-col-md2 layui-col-md-offset2">
        <shiro:user>
            <i class="iconfont icon-sixin header-letter" id="homeLetterShowBtn"></i>
        </shiro:user>
        <shiro:guest>
            <i class="iconfont icon-yonghu1 account"
               onclick="location.href='${pageContext.request.contextPath}/login'"></i>
        </shiro:guest>
        <shiro:user>
            <i class="iconfont icon-yonghu account" data-dropdown="#dropdown-standard"></i>
            <div class="dropdown-menu dropdown-anchor-top-left dropdown-has-anchor" id="dropdown-standard">
                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/home">
                            <i class="iconfont icon-faxian" style="display: inline-block"></i>
                            发现
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/make">
                            <i class="iconfont icon-tubiao09"></i>写作
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/personalCenter/<shiro:principal/>">
                            <i class="iconfont icon-yonghu1"></i>个人中心
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/setting">
                            <i class="iconfont icon-gerenshezhi"></i>设置
                        </a>
                    </li>
                    <li>
                        <a onclick="loginOut()">
                            <i class="iconfont icon-app_icons--"></i>退出
                        </a>
                    </li>
                </ul>
            </div>
        </shiro:user>
    </div>
</div>
<script type="text/javascript">
    function headerInit() {
        let rootPath = getRootPath();
        if (rootPath === "home" || rootPath === "searchHome") {
            $("#searchService").show();
        } else {
            $("#searchService").hide();
        }
    }

    function loginOut() {
        $.post("${pageContext.request.contextPath}/loginOut.do", {}, function () {
            window.location.href = "${pageContext.request.contextPath}/login";
        });
    }

    $(function () {
        headerInit();
        $("#searchContent").click(function (event) {
            event.stopPropagation();
        });

        $("#search").click(function () {
            window.location.href = "${pageContext.request.contextPath}/searchHome"
        });
    });
</script>

<script type="text/javascript">
    layui.use('layer', function () {
        let layer = layui.layer;
        $("#homeLetterShowBtn").click(function () {
            layer.open({
                type: 2,
                content: '${pageContext.request.contextPath}/chat/getLetterList',
                resize: false,
                moveOut: true,
                offset: '100px',
                area: ['450px', '550px'],
                cancel: function (index, layero) {
                    layer.close(index);
                    return false;
                }
            });

        });

        $("#writeLetterBtn").click(function () {
            writeAlert = layer.open({
                type: 1,
                content: $('#writeLetter'),
                moveOut: true,
                resize: false,
                area: '500px',
                success: function () {
                    $('#writeLetter').show();
                }
            });
        });
    });
</script>
