<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全部私信</title>
    <style type="text/css">

        .main-body {
            width: 1200px;
            margin: 0 auto;
            height: 50px;
        }

        .letter-header {
            padding: 10px;
            border-bottom: 1px solid gray;
            height: 40px;
        }

        .recent-contact {
            font-size: 14px;
            color: black;
            float: left;
            position: relative;
            top: 10px;
        }

        .user-header {
            height: 100%;
            border-radius: 6px;
        }

        .main-body {
            width: 1000px;
        }

        .letter-content {
            font-size: 14px;
        }

        .send-time {
            color: #959595;
            font-size: 12px;
        }

        .letter-operation {
            font-size: 13px;
            color: dodgerblue;
            cursor: pointer;

        }

        .letter-title span {
            font-size: 17px;
        }

        .to-alias {
            color: dodgerblue;
            cursor: pointer;
            text-decoration: none;
        }

        .to-alias:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<jsp:include page="../../public/header.jsp"/>
<jsp:include page="writeLetter.jsp"/>
<div class="main-body">
    <div class="letter-header">
        <div class="recent-contact">最近联系</div>
        <button style="float: right" class="layui-btn layui-btn-primary" id="writeLetterAllBtn">写私信</button>
    </div>
    <c:forEach items="${letterList}" var="letter">
        <div style="padding: 10px;height: 80px;" class="layui-row">
            <img class="user-header layui-col-md1" onclick="goToUser('${letter.userBaseInfo.mwId}')"
                 src="${letter.userBaseInfo.header}"/>
            <div class="layui-col-md9" style="margin-left: 10px;">
                <!--对方发给自己-->
                <c:if test="${letter.toUserBaseInfo.mwId==currentUser.mwId}">
                    <div class="letter-title">
                        <span>来自</span>
                        <span class="to-alias"
                              onclick="goToUser('${letter.userBaseInfo.mwId}')">${letter.userBaseInfo.alias}</span>
                        <span>:</span>
                    </div>
                </c:if>
                <!--自己发给对方的-->
                <c:if test="${letter.toUserBaseInfo.mwId!=currentUser.mwId}">
                    <div class="letter-title">
                        <span>我发送给</span>
                        <span class="to-alias"
                              onclick="goToUser('${letter.toUserBaseInfo.mwId}')">${letter.toUserBaseInfo.alias}</span>
                        <span>:</span>
                    </div>
                </c:if>
                <div class="letter-content">
                        ${letter.content}
                </div>
            </div>
            <div class="layui-col-md1" style="margin-left: 10px;">
                <jsp:useBean id="dateValue" class="java.util.Date"/>
                <jsp:setProperty name="dateValue" property="time" value="${letter.sendTime}"/>
                <div class="send-time"><fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/></div>
                <div style="margin-top:20px;">
                    <span class="letter-operation" onclick="deleteLetter('${letter.id}')">删除</span>
                    <span class="letter-operation"
                          onclick="replyContent('${letter.toUserBaseInfo.mwId}','${letter.toUserBaseInfo.alias}')">回复</span>
                </div>
            </div>
        </div>
    </c:forEach>
    <div id="page" style="text-align: center"></div>
</div>
<script>
    function sendLetter(mwId, content) {
        let letterJson = {
            toUserBaseInfo: {
                mwId: mwId
            },
            sendTime: new Date().getTime(),
            content: content,
            isRead: 1
        };
        $.post("${pageContext.request.contextPath}/chat/writeLetter.do", {letterJson: JSON.stringify(letterJson)}, function (result) {
            console.log("发送成功" + result);
        });
    }

    function replyContent(toMwId, alias) {
        layui.use(['layer', 'form'], function () {
            let layer = layui.layer;
            let content = "";
            layer.open({
                type: 1,
                title: "发送给:" + alias,
                moveOut: true,
                resize: false,
                area: '400px',
                move: false,
                content: "<div id=\"replyLetter\" style=\"text-align: center;padding: 10px;\">\n" +
                    "    <form class=\"layui-form\" action=\"\">\n" +
                    "        <div class=\"layui-form-item\" style=\"width:100%;height:100%;word-wrap: break-word\">\n" + content +
                    "        </div>\n" +
                    "        <div class=\"layui-form-item layui-form-text\">\n" +
                    "            <textarea name=\"replayContentText\" required lay-verify=\"required\" placeholder=\"请输入回复内容\" class=\"layui-textarea\"></textarea>\n" +
                    "        </div>\n" +
                    "        <div class=\"layui-form-item\">\n" +
                    "            <button class=\"layui-btn\" lay-submit lay-filter=\"replyContent\">回复</button>\n" +
                    "            <button type=\"reset\" class=\"layui-btn layui-btn-primary\">重置</button>\n" +
                    "        </div>\n" +
                    "    </form>\n" +
                    "</div>"
            });
            let form = layui.form;
            form.on('submit(replyContent)', function (data) {
                console.log(data.field.replayContentText)
                sendLetter(toMwId, data.field.replayContentText);
                layer.closeAll();
                return false;
            });

        });

    }

    function deleteLetter(id) {
        $.post("${pageContext.request.contextPath}/chat/deleteLetter.do", {
            id: id
        }, function (result) {
        });
        window.location.reload();
    }
    let startNum = Number.parseInt(getQueryVar("startNum"))
    let pageSize = 10;
    $(function () {
        layui.use('laypage', function () {
            let laypage = layui.laypage;
            laypage.render({
                elem: 'page'
                , count: ${letterNumber}
                , groups: 10
                , curr: (startNum + pageSize) / pageSize
                , jump: function (obj, first) {
                    let href = "${pageContext.request.contextPath}/chat/getAllLetter?startNum=" + (obj.curr - 1) * pageSize + "&pageSize=" + pageSize;
                    if (!first) {
                        window.location.href = href;
                    }

                }
            });

            $("#writeLetterAllBtn").click(function () {
                writeAlert = layer.open({
                    type: 1,
                    content: $('#writeLetter'),
                    moveOut: true,
                    resize: false,
                    area: '500px',
                    success: function (layero, index) {
                        $('#writeLetter').show();
                    }
                });
            });
        });
    });
</script>
</body>
</html>
