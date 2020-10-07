<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style type="text/css">
    .badge {
        margin-top: 8px;
    }

    .letter-title {
        margin: 0 auto;
        text-align: center;
        font-size: 18px;
        cursor: pointer;
        height: 35px;
        border-bottom: 1px #d8d5f6 solid;
    }

    .letter-title:hover {
        color: #918f8f;
    }

    .letter-content {
        overflow-y: hidden;
        height: 420px;
        overflow-x: hidden;
    }

    .letter-footer {
        padding: 10px;
        border-top: 1px #d8d5f6 solid;
        height: 10px;
    }

    .writer-letter-btn {
        font-size: 16px;
        display: inline-block;
        cursor: pointer;
    }

    .letter-title-picture {
        width: 50px;
        height: 50px;
        cursor: pointer;
    }

    .letter-detail {
        display: inline-block;
        height: 50px;
        margin-left: 10px;
        cursor: pointer;
    }

    .letter-detail-title {
        font-size: 15px;
        font-weight: 500;
        color: black;
        cursor: pointer;
    }

    .letter-detail-content {
        font-size: 14px;
        color: #8590a5;
        cursor: pointer;
    }

    .look-all-letter {
        float: right;
        font-size: 16px;
        cursor: pointer;
    }

    .letter-tip {
        display: block;
        background: #f00;
        border-radius: 50%;
        width: 8px;
        height: 8px;
        top: 5px;
        left: 35px;
        position: relative;
    }
</style>
<jsp:include page="../../public/public.jsp"/>
<div class="badge" id="showLetter">
    <div class="letter-title">我的私信</div>
    <div class="letter-content" id="letterContent">
        <c:forEach items="${letterList}" var="letter">
            <div style="padding: 10px;"
                 onclick="getLetter('${letter.id}','${letter.userBaseInfo.mwId}','${letter.userBaseInfo.alias}','${letter.content}')">
                <div style="float:left;">
                    <img src="${letter.toUserBaseInfo.header}" class="letter-title-picture"/>
                </div>
                <div class="letter-detail">
                    <div class="letter-detail-title">${letter.userBaseInfo.alias}</div>
                    <div class="letter-detail-content">
                        <div>
                            <c:if test="${letter.content.length()>18}">
                                ${fn:substring(letter.content,0,18)}...
                            </c:if>
                            <c:if test="${letter.content.length()<=18}">
                                ${letter.content}
                            </c:if>
                        </div>
                    </div>
                </div>
                <div style="display:inline-block;float:right;height: 50px;width:80px;">
                    <jsp:useBean id="dateValue" class="java.util.Date"/>
                    <jsp:setProperty name="dateValue" property="time" value="${letter.sendTime}"/>
                    <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/>
                    <span class="letter-tip"></span>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="letter-footer">
        <div class="writer-letter-btn">
            <i class="iconfont icon-tubiao09" style="font-size: 16px;"></i>
            <span id="writeLetterBtn">写私信</span>
        </div>
        <div class="look-all-letter" onclick="getAllLetter()">查看所有私信</div>
    </div>
</div>
<jsp:include page="writeLetter.jsp"/>
<script type="text/javascript">
    function getAllLetter() {
        parent.location.href = "${pageContext.request.contextPath}/chat/getAllLetter";
    }

    function readLetter(id) {
        $.post("${pageContext.request.contextPath}/chat/readLetter.do", {
            id: id
        }, function (result) {
        });
    }

    function getLetter(id, mwId, alias, content) {
        readLetter(id);
        //查看readLetter
        layui.use(['layer', 'form'], function () {
            layer.open({
                type: 1,
                title: "来自:" + alias,
                moveOut: true,
                resize: false,
                area: '400px',
                move: false,
                content: "<div id=\"replyLetter\" style=\"text-align: center;padding: 10px;\">\n" +
                    "    <form class=\"layui-form\" action=\"\">\n" +
                    "        <div class=\"layui-form-item\" style=\"width:100%;height:100%;word-wrap: break-word\">\n" + content +
                    "        </div>\n" +
                    "        <div class=\"layui-form-item layui-form-text\">\n" +
                    "            <textarea name=\"replayContentText\"  placeholder=\"请输入回复内容\" class=\"layui-textarea\"></textarea>\n" +
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
                let content =  data.field.replayContentText;
                if(trim(content)==""){
                    layer.msg("请输入发送内容", {icon: 7,offset: '100px'});
                    return false;
                }
                let letterJson = {
                    toUserBaseInfo: {
                        mwId: mwId
                    },
                    sendTime: new Date().getTime(),
                    content:content,
                    isRead: 1
                };
                $.post("${pageContext.request.contextPath}/chat/writeLetter.do", {letterJson: JSON.stringify(letterJson)}, function (result) {
                    if (result.code == 1) {
                        layer.msg("发送成功", {icon: 1,offset: '100px'});
                    } else {
                        layer.msg(result.message, {icon: 5,offset: '100px'});
                    }
                });
                layer.closeAll();
                return false;
            });
        });
    }

    layui.use('layer', function () {
        let layer = layui.layer;
        $("#writeLetterBtn").click(function () {
            writeAlert = layer.open({
                type: 1,
                content: $('#writeLetter'),
                moveOut: true,
                resize: false,
                area: '400px',
                move: false,
                success: () => {
                    $('#writeLetter').show();
                },
                end: () => {
                }
            });
        });
    });
</script>
