<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章加密</title>
</head>
<jsp:include page="../public/public.jsp"/>
<body>
<form class="layui-form" action="" style="display: none">
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label">文章密码</label>
        <div class="layui-input-block">
            <input type="password" name="pwd" required lay-verify="required" autocomplete="off"
                   class="layui-input" style="width: 340px;">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">验证码</label>
        <div>
            <input type="text" name="code" required lay-verify="required" autocomplete="off"
                   class="layui-input" style="width: 200px;">
            <img id="codeImage" style="width: 100px;height: 50px;position: absolute;left: 350px;top: 60px;">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="encryptionFrom">提交</button>
            <span id="errorText" style="color: red;margin-left: 30px;"></span>
        </div>
    </div>
</form>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>
    let articleId = window.document.location.pathname.substring(window.document.location.pathname.lastIndexOf("/") + 1)
    let codeKey = "";

    function init() {
        layui.use(['layer'], function () {
            let layer = layui.layer;
            layer.open({
                type: 1,
                offset: '100px',
                area: ['500px', '250px'],
                title: "文章被加密了",
                content: $("form")
            });
        });
    }

    $(function () {
        getCode();
        init();
        $("#codeImage").click(function () {
            getCode();
        });
    });

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
            $("#errorText").text("获取验证码失败");
        });
    }


    layui.use(['form', 'layer'], function () {
        let form = layui.form;
        //监听提交
        form.on('submit(encryptionFrom)', function (data) {
            $.post("${pageContext.request.contextPath}/isGetArticle.do", {
                articleId: articleId,
                pwd: data.field.pwd,
                key: codeKey,
                code: data.field.code
            }, function (result) {
                if (result.code != 1) {
                    $("#errorText").text(result.message);
                } else {
                    window.location.href = "${pageContext.request.contextPath}/getArticleByPwd?articleId=" + articleId
                        + "&pwd=" + data.field.pwd + "&key=" + codeKey + "&code=" + data.field.code;
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
