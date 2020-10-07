package com.iflytek.front.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.exception.DeleteFileException;
import com.iflytek.common.exception.UploadFileException;
import com.iflytek.common.model.Article;
import com.iflytek.common.model.Result;
import com.iflytek.config.service.other.FastDFSClient;
import com.iflytek.front.web.service.FArticleService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
/**
 * @author AZhao
 */
@Controller
public class ArticleMakeController {
    @Autowired
    private FArticleService fArticleService;
    @Autowired
    private FastDFSClient fastDFSClient;
    private Logger logger = LoggerFactory.getLogger(ArticleMakeController.class);

    @RequestMapping(value = "/make")
    public String make() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal()==null){
            return "page/user/login";
        }
        return "page/makeDetail";
    }

    @RequestMapping(value = "/uploadArticle.do")
    @ResponseBody
    public Result uploadArticle(@ModelAttribute Article article) {
        logger.info(article + "");
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result().failure(ResultCode.UnLogin);
        }
        return fArticleService.uploadArticle(article);
    }

    @ApiOperation(value = "媒体文件上传", notes = "富文本编辑器", httpMethod = "POST")
    @RequestMapping(value = "/uploadMedia")
    @ResponseBody
    public String uploadMedia(MultipartFile file) throws UploadFileException {
        Result<String> result = uploadFile(file);
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        if (result.getCode() == 1 || subject.getPrincipal() == null) {
            map.put("code", 0);//0表示成功，1失败
        } else {
            map.put("code", 1);//0表示成功，1失败
        }
        Map<String, Object> map2 = new HashMap<>();
        map.put("msg", "success");//提示消息
        map.put("data", map2);
        map2.put("src", result.getData());//图片url
        map2.put("title", file.getOriginalFilename());//图片名称，这个会显示在输入框里
        return new JSONObject(map).toString();
    }
    @ApiOperation(value = "媒体文件删除", notes = "富文本编辑器", httpMethod = "POST")
    @RequestMapping(value = "/deleteMedia")
    @ResponseBody
    public String deleteMedia(@RequestParam(value = "imgpath", required = false) String imgpath,
                              @RequestParam(value = "filepath", required = false) String filepath) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        if (subject.getPrincipal() == null) {
            map.put("code", 1);
        } else {
            map.put("code", 0);
        }
        map.put("msg", "success");//提示消息
        map.put("data", map2);
        try {
            if (imgpath != null) {
                deleteFile(imgpath);
            }
            if (filepath != null) {
                deleteFile(filepath);
            }
        } catch (DeleteFileException e) {
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "fail");//提示消息
        }
        return new JSONObject(map).toString();
    }

    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public Result<Integer> deleteFile(String url) throws DeleteFileException {
        Result<Integer> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        return result.success(fastDFSClient.deleteFile(fastDFSClient.getDFSFile(url)));
    }

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public Result<String> uploadFile(MultipartFile file) throws UploadFileException {
        Result<String> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        return result.success(fastDFSClient.getUrl(fastDFSClient.uploadFileWithMultipart(file)));
    }
}
