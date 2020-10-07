<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="writeLetter" style="display: none">
    <form class="layui-form">
        <div class="layui-form-item" style="padding: 15px;">
            <input type="text" name="toMwId" required lay-verify="required" placeholder="请输入目标人美文Id" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-form-item" style="padding: 15px;margin-top: -40px;">
            <textarea name="content" required lay-verify="required" placeholder="请输入内容"
                      class="layui-textarea"></textarea>
        </div>
        <div class="layui-form-item" style="text-align: center">
            <button class="layui-btn" lay-submit lay-filter="sendLetterForm" style="width: 100px;">发送</button>
            <button type="reset" class="layui-btn layui-btn-primary" style="width: 100px;">重置</button>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/util.js"></script>
<script type="text/javascript">
    let writeAlert;
    layui.use('form', function () {
        let form = layui.form;
        //监听提交
        form.on('submit(sendLetterForm)', function (data) {
            let letter = {
                toUserBaseInfo: {
                    mwId: data.field.toMwId
                },
                sendTime: getDate(),
                content: data.field.content,
                isRead: 1
            };
            $.post("${pageContext.request.contextPath}/chat/writeLetter.do", {letterJson: JSON.stringify(letter)}, function (result) {
                if (result.code == 1) {
                    layer.msg("发送成功", {icon: 1,offset: '100px'});
                    layer.close(writeAlert);
                } else {
                    layer.msg(result.message, {icon: 5,offset: '100px'});
                }
            });
            return false;
        });
    });
</script>