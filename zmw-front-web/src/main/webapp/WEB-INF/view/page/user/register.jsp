<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/user.css">
</head>
<jsp:include page="../../public/public.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
<body>
<div>
    <jsp:include page="user.jsp"/>
    <div class="form-container" style="width:320px;height: 320px ">
        <div>
            <div class="title">美文</div>
            <div class="describe">注册美文,记录美好生活</div>
        </div>
        <div class="layui-row" style="margin-top: 10px;">
            <i class="iconfont icon-yonghu layui-col-md1 icon-input"></i>
            <div class="layui-input-inline layui-col-md10" style="margin-left: 20px;">
                <input id="phone" type="text" name="username" required lay-verify="required" placeholder="请输入手机号"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-row input-row">
            <i class="iconfont icon-mima layui-col-md1 icon-input"></i>
            <div class="layui-input-inline layui-col-md10" style="margin-left: 20px;">
                <input type="password" id="password" required lay-verify="required" placeholder="请输入密码"
                       class="layui-input ">
            </div>
        </div>
        <div class="layui-row input-row">
            <i class="iconfont icon-yanzhengma layui-col-md1 icon-input"></i>
            <div class="layui-col-md5" style="margin-left: 20px;">
                <input type="text" name="title" required lay-verify="required" placeholder="请输入验证码"
                       class="layui-input">
            </div>
            <div class="layui-col-md5">
                <span style="color: #0000FF;line-height: 37px;margin-left: 10px;">获取验证码</span>
            </div>
        </div>
        <div class="input-row" style="text-align: center">
            <button class="layui-btn layui-btn-normal" id="register" style="width: 200px;">注册</button>
        </div>
        <div style="text-align: center;">
            <span style="color: white">注册完成立即<a href="${pageContext.request.contextPath}/login">登录</a></span>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.use(['layer'], function () {
        $("#register").click(function () {
            let phone = $("#phone").val();
            if (trim(phone) == '') {
                layer.msg('请输入手机号', {icon: 7,offset: '100px'});
                return;
            }
            let password = $("#password").val();
            if (trim(password) == '') {
                layer.msg('请输入密码', {icon: 7,offset: '100px'});
                return;
            }
            let code = 1;
            let registerIndex = layer.load();
            $.post("${pageContext.request.contextPath}/register.do", {
                username: phone,
                pwd: password,
                code: code
            }, function (response) {
                if (response.code != 1) {
                    layer.msg(response.message, {icon: 5,offset: '100px'});
                } else {
                    layer.msg('注册成功', {icon: 1,offset: '100px'});
                }
                layer.close(registerIndex)
            })
        });
    });

</script>
</body>
</html>
