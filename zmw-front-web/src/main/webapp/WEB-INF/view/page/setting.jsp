<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设置</title>
    <style type="text/css">
        body {
            background-color: #f2e9f7;
        }
        .main-body {
            width: 800px;
            background-color: white;
            margin: 0 auto;
        }
    </style>
</head>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/static/assets/layui/laydate/laydate.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/laydate/laydate.js"></script>
<body>
<jsp:include page="../public/header.jsp"/>
<div class="main-body">
    <div class="layui-tab layui-tab-brief">
        <ul class="layui-tab-title">
            <li class="layui-this">个人信息</li>
            <li>账户密码</li>
        </ul>
        <div class="layui-tab-content" style="height: 380px;">
            <div class="layui-tab-item layui-show">
                <form class="layui-form" action="">
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户名</label>
                        <div class="layui-input-block">
                            <input type="text" name="alias" value="${userBaseInfo.alias}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">单选框</label>
                        <div class="layui-input-block">
                            <input type="radio" name="sex" value="男" title="男">
                            <input type="radio" name="sex" value="女" title="女">
                            <input type="radio" name="sex" value="未知" title="未知" checked>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">微信号</label>
                        <div class="layui-input-block">
                            <input type="text" name="wechat" value="${userBaseInfo.wechat}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item layui-inline">
                        <label class="layui-form-label">出生日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="birthday" value="${userBaseInfo.birthday}" lay-verify="date"
                                   class="layui-input" id="birthday"
                                   placeholder="yyyy-MM-dd">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">描述</label>
                        <div class="layui-input-block">
                            <textarea name="introduction" class="layui-textarea">${userBaseInfo.introduction}</textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="userForm">确认修改</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="layui-tab-item">
                <form class="layui-form" action="">
                    <div class="layui-form-item">
                        <label class="layui-form-label">原始密码</label>
                        <div class="layui-input-block">
                            <input type="password" name="originalPwd" required lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">新密码</label>
                        <div class="layui-input-block">
                            <input type="password" name="newPwd1" required lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">新密码</label>
                        <div class="layui-input-block">
                            <input type="password" name="newPwd2" required lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="pwdForm">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('input:radio[name="sex"][value="${userBaseInfo.sex}"]').prop('checked', true);
    })
</script>
<script>
    layui.use(['element', 'form', 'laydate','layer'], function () {
        let element = layui.element;
        let form = layui.form;
        form.on('submit(pwdForm)', function (data) {
            let originalPwd = data.field.originalPwd;
            let newPwd1 = data.field.newPwd1;
            let newPwd2 = data.field.newPwd2;
            if (newPwd1 !== newPwd2) {
                layer.msg("两次密码输入不一致",{icon:7,offset:'100px'});
            }
            $.post("${pageContext.request.contextPath}/changePwd.do", {
                originalPwd: originalPwd,
                newPwd: newPwd2
            }, function (result) {
                if(result.code==1){
                    layer.msg("修改成功",{icon:1,offset:'100px'});
                }else {
                    layer.msg(result.message,{icon:7,offset:'100px'});
                }
            });
            return false;
        });

        form.on('submit(userForm)', function (data) {
            let userBaseInfoJson = JSON.stringify(data.field);
            $.post("${pageContext.request.contextPath}/changeUserBaseInfo.do", {
                userBaseInfoJson: userBaseInfoJson
            }, function (result) {
                if(result.code==1){
                    layer.msg("修改成功",{icon:1,offset:'100px'});
                }else {
                    layer.msg(result.message,{icon:7,offset:'100px'});
                }
            });
            return false;
        });

        let laydate = layui.laydate;
        laydate.render({
            elem: '#birthday' //指定元素
        });
    });
</script>
</body>
</html>
