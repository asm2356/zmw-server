<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<style type="text/css">
    .reply-header {
        width: 32px;
        height: 32px;
        border-radius: 50%;
        float: right;
    }

    .comment-header {
        width: 32px;
        height: 32px;
        border-radius: 50%;
        margin-left: 32px;
    }

    .lookComment {
        margin: 10px auto 0;
        background-color: #d8d5f6;
        color: #406599;
        padding: 10px;
        text-align: center;
        cursor: pointer;
        border-radius: 5px;
    }

    .discuss-alias {
        font-size: 14px;
        color: #527f99;
        cursor: pointer;
        font-weight: bold;
    }

    .comment-time {
        font-size: 12px;
        color: gray;
    }

    .comment-content {
        font-size: 14px;
        margin-top: 8px;
        word-wrap: break-word;
    }

    .discuss-reply {
        color: #1c96ff;
        cursor: pointer;
        margin-top: 8px;
        font-size: 14px;
        display: none;
    }

    .reply-number {
        position: relative;
        left: 58px;
        top: 25px;
    }

    .comment-number {
        position: relative;
        left: 63px;
        top: 23px;
    }

    .discuss-praise {
        font-size: 20px;
        position: relative;
        left: 40px;
        top: 40px;
        cursor: pointer;
    }

    .word-prompt span {
        color: orange;
        font-weight: bold
    }

    .cancel-comment {
        margin-right: 5px;
        color: #838b85;
        cursor: pointer;
    }

    .cancel-comment:hover {
        color: dodgerblue;
    }

    .reply-discuss {
        margin-top: 5px;
        display: none;
    }

    .discuss-container {
        background-color: white;
    }

    .text-limit {
        position: absolute;
        margin-left: 680px;
        margin-top: -25px;
    }

    .comment-btn {
        float: right;
        margin-top: 5px;
        margin-bottom: 5px;
    }

    .target-user {
        color: dodgerblue;
        cursor: pointer;
    }

    .input-tips {
        position: absolute;
        margin-left: 480px;
        margin-top: -25px;
    }

    .replyTextareaDiv {
        display: none;
    }
</style>
<div>
    <div class="discuss-container">
        <div>
            <textarea name="articleDiscuss" placeholder="请输入内容" class="layui-textarea"></textarea>
            <%--<p class="text-limit">--%>
            <%--<span>还可以输入</span>--%>
            <%--<span style="color: orange">500</span>字--%>
            <%--</p>--%>
            <button class="comment-btn layui-btn" id="articleDiscussBtn">评论</button>
        </div>
        <c:forEach items="${discussList}" var="discuss">
            <div class="all-comment">
                <!--评论-->
                <div class="layui-row">
                    <div class="layui-col-md1">
                        <img class="comment-header" src="${discuss.userBaseInfo.header}"/>
                    </div>
                    <div class="layui-col-md9" style="margin-left: 10px;">
                        <div>
                            <span class="discuss-alias">${discuss.userBaseInfo.alias}</span>
                            <jsp:useBean id="discussTime" class="java.util.Date"/>
                            <jsp:setProperty name="discussTime" property="time" value="${discuss.discussTime}"/>
                            <span class="comment-time"><fmt:formatDate type="date" value="${discussTime}"
                                                                       pattern="MM-dd HH:mm"/></span>
                        </div>
                        <div class="comment-content">
                                ${discuss.content}
                        </div>
                        <div style="margin-top: 5px;color: dodgerblue;cursor: pointer">
                            <span onclick="replyTextBtn(this)">回复   </span>
                            <c:if test="${discuss.replyDiscussList.size()>0}">
                                <span data-cur="false" onclick="packReplyBtn(this)">展开回复</span>
                            </c:if>
                        </div>
                        <div class="replyTextareaDiv">
                            <textarea placeholder="请输入回复的内容" class="layui-textarea replyTextarea"></textarea>
                            <div>
                                    <%--<p class="input-tips">还可以输入--%>
                                    <%--<span>100</span>字--%>
                                    <%--</p>--%>
                                <div style="float: right">
                                    <span class="cancel-comment" onclick="discussCancelBtn(this)">取消</span>
                                    <button class="layui-btn  layui-btn-sm"
                                            onclick="discussBtn(this,'${discuss.discussId}')">评论
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md1" onclick="discussPraise(this,'${discuss.discussId}')">
                        <div>
                            <i class="iconfont icon-zan2 discuss-praise" style="font-size: 20px;"></i>
                        </div>
                        <div class="comment-number" data-discuss="false">${discuss.praiseNumber}</div>
                    </div>
                </div>

                <!--回复评论-->
                <c:forEach items="${discuss.replyDiscussList}" var="reply">
                    <div class="reply-discuss layui-row" style="margin-left: 40px;">
                        <div class="layui-col-md1">
                            <img class="reply-header" src="${reply.userBaseInfo.header}"/>
                        </div>
                        <div class="layui-col-md9" style="margin-left: 10px;">
                            <div>
                                <span class="alias">${reply.userBaseInfo.alias}</span>
                                <jsp:useBean id="replyTime" class="java.util.Date"/>
                                <jsp:setProperty name="replyTime" property="time" value="${reply.discussTime}"/>
                                <span class="comment-time"><fmt:formatDate type="date" value="${replyTime}"
                                                                           pattern="MM-dd HH:mm"/></span>
                            </div>
                            <c:if test="${reply.toContent==null}">
                                <div class="comment-content">
                                        ${reply.content}
                                </div>
                            </c:if>
                            <c:if test="${reply.toContent!=null}">
                                <div class="comment-content">
                                        ${reply.content}
                                    <span class="target-user">@${reply.targetUserBaseInfo.alias}</span>
                                    : ${reply.toContent}
                                </div>
                            </c:if>

                            <div style="margin-top: 8px;color: dodgerblue;cursor: pointer"
                                 onclick="siblingReply(this)">回复
                            </div>
                            <!--输入框输入-->
                            <div class="siblingReplyDiv discuss-reply">
                                <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
                                    <%--<p class="input-tips"--%>
                                    <%--style="position: absolute;margin-left: 450px;margin-top: -25px;">--%>
                                    <%--还可以输入--%>
                                    <%--<span>100</span>字--%>
                                    <%--</p>--%>
                                <div style="float: right">
                                    <span class="cancel-comment" onclick="siblingCancel(this)">取消</span>
                                    <button class="layui-btn  layui-btn-sm"
                                            onclick="siblingComment(this,'${discuss.discussId}','${reply.userBaseInfo.mwId}','${reply.content}')">
                                        评论
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md1" onclick="replyPraise(this,'${reply.replyDiscussId}')">
                            <i class="iconfont discuss-praise icon-dianzan_active" style="margin-left: -5px;"></i>
                            <div class="reply-number" data-reply="false">${reply.replyPraiseNumber}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        <c:if test="${discussNumber>10} ">
            <div style="text-align: center">
                <div id="discussPage"></div>
            </div>
        </c:if>
        <br/><br/>
    </div>
</div>
<script>
    let startNum = Number.parseInt(getQueryVar("startNum"))
    let pageSize = 10;
    layui.use(['laypage', 'layer'], function () {
        let laypage = layui.laypage;
        let layer = layui.layer;
        laypage.render({
            elem: 'discussPage'
            , count: ${discussNumber} //数据总数，从服务端得到
            , groups: 8
            , curr: (startNum + pageSize) / pageSize
            , jump(obj, first) {
                let href = "${pageContext.request.contextPath}/articleShow/${article.articleId}?startNum=" + (obj.curr - 1) * pageSize + "&pageSize=" + pageSize;
                if (!first) {
                    window.location.href = href;
                }
            }
        });
        //评论文章
        $("#articleDiscussBtn").click(function () {
            let content = trim($("textarea[name='articleDiscuss']").val());
            if (content==null || content == "") {
                layer.msg("评论内容不能为空");
                return;
            }
            $.post("${pageContext.request.contextPath}/writeDiscuss.do", {
                articleId: '${article.articleId}',
                content: content
            }, function (result) {
                //要刷新页面
                if (result.code != 1) {
                    layer.msg(result.message, {icon: 5,offset: '100px'});
                } else {
                    layer.msg("发表成功", {icon: 1,offset: '100px'});
                    window.location.reload();
                }
            });
        });

    });
</script>
<script type="text/javascript">
    // 回复主评论人
    function replyTextBtn(cur) {
        let obj = $(cur).parent().next();
        $(".replyTextareaDiv").hide();
        $(obj).show();
    }

    // 取消回复主评论人
    function discussCancelBtn(cur) {
        $(".replyTextareaDiv").hide();
    }

    //回复主评论人
    function discussBtn(cur, discussId) {
        layui.use(['layer'], function () {
            let layer = layui.layer;
            let content = $(cur).parents(".replyTextareaDiv").children("textarea").val();
            if (content == "") {
                layer.msg("请输入要回复的内容",{offset: '100px'});
                return;
            }
            $.post("${pageContext.request.contextPath}/replyComment.do", {
                discussId: discussId,
                content: content
            }, function (result) {
                if (result.code != 1) {
                    layer.msg(result.message, {icon: 5,offset: '100px'});
                } else {
                    layer.msg("发表成功", {icon: 1,offset: '100px'});
                    window.location.reload();
                }
            });
        });
    }

    // 子评论人 之间的相互评论
    function siblingReply(cur) {
        $(".siblingReplyDiv").hide();
        let obj = $(cur).next();
        $(obj).show();
    }

    function siblingCancel() {
        $(".siblingReplyDiv").hide();
    }

    // 子评论人 之间的相互评论 ajax
    function siblingComment(cur, discussId, targetUserMwId, toContent) {
        layui.use(['layer'], function () {
            let layer = layui.layer;
            let content = $(cur).parents(".siblingReplyDiv").children("textarea").val();
            $.post("${pageContext.request.contextPath}/toReplyDiscuss.do", {
                discussId: discussId,
                targetUserMwId: targetUserMwId,
                toContent: toContent,
                content: content
            }, function (result) {
                if (result.code != 1) {
                    layer.msg(result.message, {icon: 5,offset: '100px'});
                } else {
                    layer.msg("发表成功", {icon: 1,offset: '100px'});
                    window.location.reload();
                }
            });
        });
    }

    //给评论人点赞
    function discussPraise(cur, discussId) {
        layui.use(['layer'], function () {
            let layer = layui.layer;
            let obj = $(cur).children(".comment-number");
            let number = Number.parseInt($(obj).html());
            let dataCur = $(obj).data("discuss");
            if (!dataCur) {
                number = number + 1;
            } else {
                number = number - 1;
            }
            dataCur = !dataCur;
            $.post("${pageContext.request.contextPath}/updateDiscussPraise.do", {
                discussId: discussId,
                isIncrement: dataCur
            }, function (result) {
                $(obj).data("discuss", dataCur);
                $(obj).html(number);
            });
        });
    }

    //给自评论人点赞
    function replyPraise(cur, replyId) {
        layui.use(['layer'], function () {
            let layer = layui.layer;
            let obj = $(cur).children(".reply-number");
            let number = Number.parseInt($(obj).html());
            let dataCur = $(obj).data("reply");
            if (!dataCur) {
                number = number + 1;
            } else {
                number = number - 1;
            }
            dataCur = !dataCur;
            $.post("${pageContext.request.contextPath}/updateReplyDiscussPraise.do", {
                replyDiscussId: replyId,
                isIncrement: dataCur
            }, function (result) {
                $(obj).data("reply", dataCur);
                $(obj).html(number);
            });
        });
    }

    function packReplyBtn(cur) {
        let reply = $(cur).parents(".all-comment").children(".reply-discuss");
        let dataCur = $(cur).data("cur");
        console.log(dataCur);
        if (dataCur) {
            $(cur).html("展开回复");
            $(reply).hide();
        } else {
            $(cur).html("收起回复");
            $(reply).show();

        }
        dataCur = !dataCur;
        $(cur).data("cur", dataCur);
    }
</script>
