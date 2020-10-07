<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/layui/ace.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/wx-audio/wx-audio.css">
<html>
<head>
    <title>写作</title>
    <style type="text/css">
        .main-body {
            width: 1100px;
            margin: 20px auto 0;
        }

        form {
            margin-top: 10px;
            margin-bottom: 30px;
        }

        .wx-audio {
            height: 50px;
            display: inline-block;
        }
    </style>
</head>
<body>
<jsp:include page="../public/header.jsp"/>
<div class="main-body">
    <input type="text" class="layui-input" id="title" placeholder="请输入文章标题"/>
    <textarea id="editor"></textarea>
    <form class="layui-form" action="" onsubmit="return false;">
        <div class="layui-form-item">
            <button type="button" class="layui-btn" id="titlePictureBtn">
                <i class="layui-icon layui-icon-picture"></i>文章封面
            </button>
            <img id="titlePictureImg" style="width: 200px;height: auto">
            <input type="file" style="display: none;" id="titlePictureFile" accept="image/*"/>
        </div>

        <div class="layui-form-item">
            <button type="button" class="layui-btn" id="musicBtn">
                <i class="layui-icon">&#xe67c;</i>背景音乐
            </button>
            <input id="musicFile" type="file" style="display: none" accept="audio/mpeg">
            <span id="musicUrl" style="display: none"></span>
            <div class="wx-audio" id="musicAudio"></div>
        </div>
        <div class="layui-form-item" style="margin-top: 40px;">
            <label class="layui-form-label">类别</label>
            <div class="layui-input-inline">
                <select name="category" id="category" lay-verify="required">
                    <option value="情感">情感</option>
                    <option value="摄影">摄影</option>
                    <option value="随笔">随笔</option>
                    <option value="哲理">哲理</option>
                    <option value="其他">其他</option>
                </select>
            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">发布时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="datetime">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">公开度</label>
            <div class="layui-input-inline" style="width:400px;">
                <input type="radio" name="openness" value="1" title="公开" checked="checked" lay-filter="openness">
                <input type="radio" name="openness" value="2" title="私密" lay-filter="openness">
                <input type="radio" name="openness" value="3" title="加密" lay-filter="openness">
            </div>
            <div class="layui-inline" id="pwdGroup" style="display: none;">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <input type="password" id="pwd" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-normal" id="submit">提交</button>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/wx-audio/wx-audio.js"></script>

<script type="text/javascript">
    let articleContent = "";
    layui.use(['laydate', 'layedit', 'jquery', 'upload', 'form'], function () {
        let $ = layui.jquery;
        let laydate = layui.laydate;
        laydate.render({
            elem: '#datetime'
            , type: 'datetime'
            , trigger: 'click'
        });

        let layedit = layui.layedit;
        layedit.set({
            uploadImage: {
                url: '${pageContext.request.contextPath}/uploadMedia' //接口url
                , type: 'post' //默认post
                , accept: 'image'
                , acceptMime: 'image/*'
                , size: 62914560
                , exts: 'jpg|png|gif|bmp|jpeg'
                , done: function (data) {
                    console.log(data)
                }
            },
            uploadVideo: {
                url: '${pageContext.request.contextPath}/uploadMedia'
                , accept: 'video'
                , acceptMime: 'video/*'
                , exts: 'mp4|flv|avi|rm|rmvb'
                , size: 62914560
                , done: function (data) {

                }
            },
            calldel: {
                url: '${pageContext.request.contextPath}/deleteMedia'
                , done: function (data) {

                }
            },
            onchange: function (content) {
                articleContent = content;

            }
        });
        layedit.build('editor', {
            tool: [
                'html', 'code', 'strong', 'italic', 'underline', 'del', 'addhr'
                , '|', 'removeformat', 'fontFomatt', 'fontfamily', 'fontSize', 'fontBackColor', 'colorpicker', 'face'
                , '|', 'left', 'center', 'right'
                , '|', 'link', 'unlink', 'image_alt', 'video'
                , '|'
                , 'table'
                , 'fullScreen', 'preview'
            ]
        });
        let form = layui.form;
        form.on('radio(openness)', function (data) {
            if (data.value == 3) {
                $("#pwdGroup").show();
            } else {
                $("#pwdGroup").hide();
            }
        });
        let wxAudio = new WxAudio({
            ele: '#musicAudio',
            title: '',
            disc: '',
            src: '',
            width: '320px',
            ended: function () {
            }
        });
        $("#musicBtn").click(function () {
            $("#musicFile").click();
        });
        $("#musicFile").change(function () {
            let formData = new FormData();
            formData.append("file", $("#musicFile")[0].files[0]);
            let musicUrl = $("#musicUrl").text();
            if (musicUrl != "") {
                deleteFile(musicUrl);
            }
            $.ajax({
                url: '${pageContext.request.contextPath}/uploadFile',
                type: 'post',
                data: formData,
                contentType: false,
                processData: false,
                success: function (result) {
                    if (result.code == 1) {
                        $("#musicUrl").text(result.data);
                        wxAudio.audioCut(result.data, $("#musicFile")[0].files[0].name, '')
                    } else {
                        layer.msg("加载失败", {icon: 5, offset: '100px'})
                    }
                }
            })
        });

        $("#titlePictureBtn").click(function () {
            $("#titlePictureFile").click();
        });
        $("#titlePictureFile").change(function () {
            let url = $("#titlePictureImg").attr("src");
            if (url != null) {
                deleteFile(url);
            }
            let formData = new FormData();
            formData.append("file", $("#titlePictureFile")[0].files[0]);
            $.ajax({
                url: "${pageContext.request.contextPath}/uploadFile",
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function () {
                },
                success: function (response) {
                    $("#titlePictureImg").attr("src", response.data);
                },
                error: function (responseStr) {
                    console.log("error");
                    console.log(responseStr);
                }
            });
        });
        $("input[type='radio'][name='openness']").change(function () {
            console.log("xxxxxxx")
        });

        //上传
        $("#submit").on("click", function () {
            let formData = new FormData();
            let title = $("#title").val();
            if (title == null || title === "") {
                layer.msg("标题不能为空", {icon: 7, offset: '100px'});
                return false;
            }
            if (trim(articleContent) == "") {
                layer.msg("发表的内容不能为空", {icon: 7, offset: '100px'});
                return false;
            }
            let category = $("#category").val();
            let titlePictureImg = $("#titlePictureImg").attr("src");
            if (titlePictureImg == null || titlePictureImg == "") {
                layer.msg("请选择文章封面", {icon: 7, offset: '100px'});
                return;
            }
            formData.append("titlePicture", titlePictureImg);
            let openness = $("input[name='openness']:checked").val();
            formData.append("title", title);
            formData.append("openness", openness);
            formData.append("categoryName", category);
            let datetime = $("#datetime").val();
            let date = new Date(datetime);
            let releaseTime = date.getTime();
            formData.append("releaseTime", releaseTime);
            if (openness == 3) {
                let pwd = $("input[id='pwd']").val();
                if (pwd == null || pwd === "") {
                    layer.msg("文章密码不能为空", {icon: 7, offset: '100px'});
                    return false;
                }
                formData.append("pwd", pwd);
            }
            let music = $("#musicUrl").text();
            if (music != "") {
                formData.append("music", music);
            }
            formData.append("content", articleContent);
            $.ajax({
                url: "${pageContext.request.contextPath}/uploadArticle.do",
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function () {
                },
                success: function (result) {
                    if (result.code == 1) {
                        layer.msg("发布成功", {icon: 1, offset: '100px'});
                        window.location.reload();
                    } else {
                        layer.msg(result.message, {icon: 5, offset: '100px'})
                    }
                },
                error: function (response) {
                }
            });
        });
    });
</script>
</body>
</html>
