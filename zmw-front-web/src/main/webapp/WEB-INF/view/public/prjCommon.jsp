<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function goToArticle(articleId) {
        window.location.href = '${pageContext.request.contextPath}/articleShow/' + articleId;
    }

    function goToUser(mwId) {
        window.location.href = '${pageContext.request.contextPath}/personalCenter/' + mwId
    }

    function concerned(mwId) {
        layui.use('layer', function () {
            $.post("${pageContext.request.contextPath}/concernedAuthor.do", {
                mwId: mwId,
                isConcerned: true
            }, function (result) {
                if (result.code == 1) {
                    layer.msg("关注成功", {icon: 1, offset: '100px'});
                } else {
                    layer.msg(result.message, {icon: 5, offset: '100px'})
                }
            });
        })
    }

    function sendLetterToUser(mwId) {
        console.log(mwId)
        layui.use('layer', function () {
            let layer = layui.layer;
            layer.prompt({
                formType: 2,
                title: '私信',
                offset: '100px',
                area: ['250px', '100px'] //自定义文本域宽高
            }, function (value, index, elem) {
                let letter = {
                    toUserBaseInfo: {
                        mwId: mwId
                    },
                    sendTime: getDate(),
                    content: value,
                    isRead: 1
                };
                $.post("${pageContext.request.contextPath}/chat/writeLetter.do", {letterJson: JSON.stringify(letter)}, function (result) {
                    if (result.code != 1) {
                        layer.msg(result.message, {icon: 5, offset: '100px'});
                    } else {
                        layer.msg('发送成功', {icon: 1, offset: '100px'});
                    }
                    layer.close(index);
                });
            });
        });
    }

    //取消关注作者

    function cancelConcerned(mwId) {
        $.post("${pageContext.request.contextPath}/concernedAuthor.do", {
            mwId: mwId,
            isConcerned: false
        }, function (result) {
            console.log("取消成功");
            window.location.reload();
        });
    }

    function deleteFile(url) {
        $.post("${pageContext.request.contextPath}/deleteFile", {url: url}, function (result) {
            console.log(result);
        });
    }
</script>