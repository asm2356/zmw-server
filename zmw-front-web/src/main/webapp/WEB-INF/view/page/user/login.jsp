<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/user.css">
</head>
<jsp:include page="../../public/public.jsp"/>
<body>
<div>
    <jsp:include page="user.jsp"/>
    <div class="form-container" style="width:320px;height: 320px">
        <div>
            <div class="title">美文</div>
            <div class="describe">登陆美文,记录美好生活</div>
        </div>
        <div class="layui-row input-row">
            <i class="iconfont icon-yonghu layui-col-md1 icon-input"></i>
            <div class="layui-input-inline layui-col-md10" style="margin-left: 20px;">
                <input type="text" id="username" required lay-verify="required" placeholder="请输入手机号或者美文号"
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
                <input type="text" id="code" required lay-verify="required" placeholder="请输入验证码"
                       class="layui-input">
            </div>
            <div class="layui-col-md5">
                <img id="codeImage" onclick="getCode()"
                     src="${pageContext.request.contextPath}/static/img/loadingCode.gif"
                     class="verificationCodeImage"/>
            </div>
        </div>
        <div class="input-row" style="text-align: center">
            <button class="layui-btn  layui-btn-normal" id="login" style="width: 200px;">登录</button>
        </div>
        <div style="text-align: center;">
            <span style="color: white">没有账号?<a href="${pageContext.request.contextPath}/register">注册</a></span>
        </div>
    </div>
</div>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
<script type="text/javascript">
    let codeKey = "";

    function getCode() {
        axios({
            url: "/getVerificationCode.do",
            method: "post",
            responseType: 'arraybuffer',
            param: {}
        }).then((response) => {
            let verificationCodeImage = 'data:image/png;base64,' + btoa(
                new Uint8Array(response.data)
                    .reduce((data, byte) => data + String.fromCharCode(byte), '')
            );
            $("#codeImage").attr("src", verificationCodeImage);
            codeKey = response.headers.key;
        }).catch((error) => {
            layer.alert('获取验证码失败', {icon: 5});
        });
    }

    layui.use(["layer", 'form'], function () {
        let layer = layui.layer;
        getCode();
        $("#login").click(function () {
            let username = $("#username").val();
            if (username == "") {
                layer.msg('请输入手机号或者美文号...', {icon: 7,offset: '100px'});
                return;
            }
            let pwd = $("#password").val();
            if (pwd == "") {
                layer.msg('请输入密码...', {icon: 7,offset: '100px'});
                return;
            }
            let code = $("#code").val();
            if (code === "") {
                layer.msg('请输入验证码...', {icon: 7,offset: '100px'});
                return;
            }
            let loginIndex = layer.load();
            $.post("/login.do", {
                username: username,
                pwd: pwd,
                key: codeKey,
                code: code
            }, function (response) {
                layer.close(loginIndex);
                if (response.code === 10006) {
                    layer.msg("验证码错误", {icon: 5,offset: '100px'});
                    getCode();
                    $("#code").val('');
                } else if (response.code === 1) {
                    window.location.href = "${pageContext.request.contextPath}/home"
                } else {
                    getCode();
                    layer.msg(response.message, {icon: 5,offset: '100px'});
                }
            });
        });

        let form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
</body>
</html>
