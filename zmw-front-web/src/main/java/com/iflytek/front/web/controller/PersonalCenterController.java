package com.iflytek.front.web.controller;

import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.common.exception.DBException;
import com.iflytek.common.exception.DeleteFileException;
import com.iflytek.common.model.DFSFile;
import com.iflytek.common.model.Result;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.config.service.other.FastDFSClient;
import com.iflytek.manager.api.*;
import com.iflytek.search.api.SearchArticleService;
import com.iflytek.search.api.SearchUserBaseInfoService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author AZhao
 */
@Controller
public class PersonalCenterController {
    private Logger logger = LoggerFactory.getLogger(PersonalCenterController.class);
    @Autowired
    private UserContactService userContactService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserBaseInfoService userBaseInfoServic;
    @Autowired
    private CollectService collectService;
    @Autowired
    private DiscussService discussService;

    @Autowired
    private SearchUserBaseInfoService searchUserBaseInfoService;
    @Autowired
    private FastDFSClient fastDFSClient;
    @Autowired
    private UserBaseInfoService userBaseInfoService;
    @Autowired
    private SearchArticleService searchArticleService;

    @ApiOperation(value = "根据用户的别名进行模糊匹配", httpMethod = "POST")
    @RequestMapping(value = "getMatchUserBaseInfoList.do")
    public Result<List<UserBaseInfo>> getMatchUserBaseInfoList(long startNum, int pageSize, String value) {
        Result<List<UserBaseInfo>> result = new Result<>();
        return result.success(searchUserBaseInfoService.getUserBaseInfoListByAttribute(value, startNum, pageSize));
    }


    @RequestMapping(value = "personalCenter/{mwId}")
    public String personalCenter(@PathVariable(value = "mwId", required = false) String mwId,
                                 @RequestParam(value = "startNum", defaultValue = "0") int startNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                 Model model) {
        pageSize = Integer.MAX_VALUE;//TODO  分页
        UserBaseInfo userBaseInfo = userBaseInfoServic.getUserBaseInfoById(mwId);
        if (userBaseInfo == null) {
            return "error/notFound";
        }
        model.addAttribute("userBaseInfo", userBaseInfo);
        model.addAttribute("userPraiseNumber", userService.getUserPraiseNumber(mwId));
        Map<String, Object> map = new HashMap<>();
        map.put("mwId", mwId);
        map.put("openness", Openness.Open.getValue());
        model.addAttribute("articleNumber", articleService.getArticleNumber(map));
        Subject subject = SecurityUtils.getSubject();
        boolean isCurrentUser;
        if (mwId == null) {
            return "error/notFound";
        } else {
            isCurrentUser = subject.getPrincipal() != null && subject.getPrincipal().toString().equals(mwId);
        }
        model.addAttribute("isCurrentUser", isCurrentUser);

        //文章
        Map<String, Object> articleMap = new HashMap<>();
        if (subject.getPrincipal() != null && subject.getPrincipal().toString().equals(mwId)) {
            articleMap.put("openness", Openness.ALL.getValue());
        } else {
            articleMap.put("openness", Openness.Open.getValue());
        }
        articleMap.put("mwId", mwId);
        articleMap.put("startNum", startNum);
        articleMap.put("pageSize", pageSize);
        model.addAttribute("articleList", articleService.getArticleList(articleMap));
        model.addAttribute("articleNumber", articleService.getArticleNumber(articleMap));
        //关注
        model.addAttribute("noticerList", userContactService.getNoticerList(mwId, startNum, pageSize));
        model.addAttribute("noticerNumber", userContactService.getNoticerNumber(mwId));
        //粉丝
        model.addAttribute("fansList", userContactService.getFansList(mwId, startNum, pageSize));
        model.addAttribute("fansNumber", userContactService.getFansNumber(mwId));
        //收藏
        model.addAttribute("collectList", collectService.getCollectArticleList(mwId, startNum, pageSize));
        model.addAttribute("collectNumber", collectService.getCollectArticleNumber(mwId));
        //评论
        model.addAttribute("userComment", discussService.getUserCommentList(mwId, startNum, pageSize));
        model.addAttribute("discussNumber", discussService.getUserDiscussNumber(mwId));
        return "page/personCenterPage/personalCenterDetail";
    }

    @ApiOperation(value = "关注和取消关注作者", httpMethod = "POST")
    @RequestMapping(value = "concernedAuthor.do")
    @ResponseBody
    public Result<Boolean> concernedAuthor(@RequestParam(value = "mwId") String mwId,
                                           @RequestParam(value = "isConcerned", defaultValue = "true") boolean isConcerned) {
        Result<Boolean> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        boolean success = false;
        if (isConcerned) {
            try {
                success = userContactService.concernedAuthor(subject.getPrincipal().toString(), mwId);
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            success = userContactService.cancelConcernedAuthor(subject.getPrincipal().toString(), mwId);
        }
        return result.success(success);
    }

    @ApiOperation(value = "收藏和取消收藏文章", httpMethod = "POST")
    @RequestMapping(value = "collectArticle.do")
    @ResponseBody
    public Result<Boolean> collectArticle(@RequestParam(value = "articleId") String articleId,
                                          @RequestParam(value = "isCollect", defaultValue = "true") boolean isCollect) {
        Result<Boolean> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        try {
            if (isCollect) {
                collectService.collectArticle(subject.getPrincipal().toString(), articleId);
            } else {
                collectService.cancelCollectArticle(subject.getPrincipal().toString(), articleId);
            }
        } catch (DBException ignored) {
        }
        return result.success(isCollect);
    }

    @ApiOperation(value = "删除文章", httpMethod = "POST")
    @RequestMapping(value = "deleteArticle.do")
    @ResponseBody
    public Result deleteArticle(String articleId) throws DeleteFileException {
        Result result = new Result();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        articleService.deleteArticle(articleId);
        searchArticleService.deleteArticle(articleId);
        return result.success();
    }

    @ApiOperation(value = "获取用户基本信息", httpMethod = "POST")
    @RequestMapping(value = "/getUserBaseInfo.do")
    public Result<UserBaseInfo> getUserBaseInfo(String mwId) {
        Result<UserBaseInfo> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (mwId == null || mwId.equals("")) {
            if (subject.getPrincipal() == null) {
                return result.failure(ResultCode.UnLogin);
            } else {
                mwId = subject.getPrincipal().toString();
            }
        }
        UserBaseInfo userBaseInfo = userBaseInfoService.getUserBaseInfoById(mwId);
        if (userBaseInfo != null) {
            return result.success(userBaseInfo);
        } else {
            return result.failure(ResultCode.Unknown_User);
        }
    }

    @ApiOperation(value = "修改个人中心封面图片", httpMethod = "POST")
    @RequestMapping(value = "/changeCover.do")
    @ResponseBody
    public Result<String> changeCover(@RequestParam(value = "coverImg") MultipartFile multipartFile,
                                      @RequestParam(value = "oldImg") String oldImg) throws Exception {
        Result<String> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        logger.info(subject.getPrincipal() + "");
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        DFSFile newDfsFile;
        newDfsFile = fastDFSClient.uploadFileWithMultipart(multipartFile);
        logger.info("oldImg" + oldImg);
        //修改数据库
        String account = subject.getPrincipal().toString();
        UserBaseInfo userBaseInfo = userBaseInfoService.getUserBaseInfoById(account);
        userBaseInfo.setCover(fastDFSClient.getUrl(newDfsFile));
        userBaseInfoService.updateUserBaseInfo(userBaseInfo);
        try {
            fastDFSClient.deleteFile(fastDFSClient.getDFSFile(oldImg));
        } catch (Exception ignored) {

        }
        return result.success(fastDFSClient.getUrl(newDfsFile));
    }

    @ApiOperation(value = "修改个人头像", httpMethod = "POST")
    @RequestMapping(value = "/changeHeader.do")
    @ResponseBody
    public Result<String> changeHeader(@RequestParam(value = "headerImg") MultipartFile multipartFile,
                                       @RequestParam(value = "oldImg") String oldImg) throws Exception {
        Result<String> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        logger.info(subject.getPrincipal() + "");
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        try {
            fastDFSClient.deleteFile(fastDFSClient.getDFSFile(oldImg));
        } catch (Exception ignored) {
        }
        DFSFile newDfsFile = fastDFSClient.uploadFileWithMultipart(multipartFile);
        //修改数据库
        String account = subject.getPrincipal().toString();
        UserBaseInfo userBaseInfo = userBaseInfoService.getUserBaseInfoById(account);
        userBaseInfo.setHeader(fastDFSClient.getUrl(newDfsFile));
        int updateResult = userBaseInfoService.updateUserBaseInfo(userBaseInfo);
        logger.info("数据库更新" + updateResult);
        return result.success(fastDFSClient.getUrl(newDfsFile));
    }

    @ApiOperation(value = "修改文章的公开度", httpMethod = "POST")
    @RequestMapping(value = "/changeArticleOpenness.do")
    @ResponseBody
    public Result<Integer> changeArticleOpenness(String articleId, String pwd, int openness) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result<Integer>().failure(ResultCode.UnLogin);
        }
        articleService.updateOpenness(articleId, openness, pwd);
        return new Result<Integer>().success();
    }

//    @ApiOperation(value = "获取公开的的文章数量", httpMethod = "POST")
//    @RequestMapping(value = "/getOpenArticleNumber.do")
//    @Cacheable(value = "openArticleNumber", key = "#mwId")
//    @ResponseBody
//    public Result<Integer> getOpenArticleNumber(String mwId) {
//        Result<Integer> result = new Result<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("mwId", mwId);
//        map.put("openness", Openness.Open.getValue());
//        return result.success(articleService.getArticleNumber(map));
//    }

}
