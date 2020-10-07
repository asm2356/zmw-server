package com.iflytek.front.web.controller;

import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.*;
import com.iflytek.front.web.service.FArticleService;
import com.iflytek.manager.api.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author AZhao
 */
@Controller
public class ArticleShowController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private DiscussService discussService;
    @Autowired
    private FArticleService fArticleService;
    @Autowired
    private UserContactService userContactService;
    @Autowired
    private UserBaseInfoService userBaseInfoService;
    @Autowired
    private CollectService collectService;

    @ApiOperation("ajax 请求判断是否通过密码得到文章")
    @RequestMapping(value = "isGetArticle.do")
    @ResponseBody
    public Result<Article> isGetArticle(String articleId,
                                        String pwd, String key,
                                        String code) {
        return fArticleService.getArticleByPwd(articleId, pwd, key, code);
    }
    @ApiOperation(value = "获取文章根据文章编号，包括文章内容，密码通过才能获取，跳转", httpMethod = "POST")
    @RequestMapping(value = "getArticleByPwd")
    public ModelAndView getArticleByPwd(String articleId,
                                        String pwd, String key,
                                        @RequestParam(value = "startNum", defaultValue = "0") int startNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                        String code) {
        ModelAndView modelAndView = new ModelAndView();
        Result<Article> result = fArticleService.getArticleByPwd(articleId, pwd, key, code);
        modelAndView = getArticleDetail(result.getData(), startNum, pageSize, modelAndView);
        modelAndView.setViewName("page/article/articleDetail");
        return modelAndView;
    }

    private ModelAndView getArticleDetail(
            Article article,
            int startNum,
            int pageSize, ModelAndView model) {
        model.addObject("article", article);
        String articleId = article.getArticleId();
        String mwId = article.getUserBaseInfo().getMwId();
        model.addObject("articleReadingNumber", articleService.getReadingNumber(mwId));
        model.addObject("discussList", discussService.getDiscussList(articleId, 0, 10)); //默认获取10条
        Map<String, Object> map = new HashMap<>();
        map.put("mwId", mwId);
        map.put("openness", Openness.Open.getValue());
        model.addObject("articleNumber", articleService.getArticleNumber(map));
        model.addObject("readingNumber", articleService.getReadingNumber(mwId));
        model.addObject("fansNumber", userContactService.getFansNumber(mwId));

        Map<String, Object> otherArticleMap = new HashMap<>();
        otherArticleMap.put("openness", Openness.Open.getValue());
        otherArticleMap.put("mwId", article.getUserBaseInfo().getMwId());
        otherArticleMap.put("startNum", 0);
        otherArticleMap.put("pageSize", 5);
        otherArticleMap.put("timeOrder", "desc");
        List<Article> otherArticleList = articleService.getArticleList(otherArticleMap);
        model.addObject("otherArticleList", otherArticleList);

        model.addObject("discussList", discussService.getDiscussList(articleId, startNum, pageSize));
        model.addObject("discussNumber", discussService.getDiscussArticleCount(articleId));
        return model;
    }
    @ApiOperation(value = "获取文章根据文章编号，包括文章内容", httpMethod = "POST")
    @RequestMapping(value = "articleShow/{articleId}")
    public ModelAndView getArticle(@PathVariable(value = "articleId") String articleId,
                                   @RequestParam(value = "startNum", defaultValue = "0") int startNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        ModelAndView model = new ModelAndView();
        Result<Article> articleResult = fArticleService.getArticle(articleId);
        Article article = articleResult.getData();
        if (articleResult.getCode() == ResultCode.Article_Not_Open.getCode()) {
            //不公开处理
            model.setViewName("error/articleNotPublic");
        } else if (articleResult.getCode() == ResultCode.Article_Encrypt_Open.getCode()) {
            //加密 公开
            model.setViewName("page/articleEncryption");
        } else if (articleResult.getCode() == ResultCode.Article_Not_Exist.getCode()) {
            //文章 不存在 处理
            model.setViewName("error/notFound");
        } else {
            model.addObject("article", article);
            model.setViewName("page/article/articleDetail");
            model = getArticleDetail(article, startNum, pageSize, model);
        }
        return model;
    }

    @ApiOperation(value = "更新文章的赞数", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "articleId", value = "用文章Id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "isIncrement", value = "文章是否点赞 true 赞数加一", required = true, dataType = "Boolean")
    })
    @RequestMapping(value = "/updateArticlePraise.do")
    @ResponseBody
    public Result<Integer> updateArticlePraise(String articleId, boolean isIncrement) {
        Result<Integer> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        articleService.updateArticlePraise(articleId, isIncrement);
        return result.success();
    }

    @ApiOperation(value = "收藏和取消收藏文章", httpMethod = "POST")
    @RequestMapping(value = "/articleCollector.do")
    @ResponseBody
    public Result<Integer> articleCollector(String articleId, boolean isCollector) {
        Result<Integer> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        if (isCollector) {
            try {
                collectService.collectArticle(subject.getPrincipal().toString(), articleId);
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            collectService.cancelCollectArticle(subject.getPrincipal().toString(), articleId);
        }
        return result.success();
    }


    @RequestMapping(value = "/getDiscuss.do")
    public List<Discuss> getDiscussAjax(String articleId, int startNum, int pageSize) {
        return discussService.getDiscussList(articleId, startNum, pageSize);
    }

    @RequestMapping(value = "writeDiscuss.do")
    @ApiOperation(value = "写评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "要评论的文章编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = true, dataType = "String")
    })
    @ResponseBody
    public Result<Map<String, Object>> writeDiscuss(String articleId, String content) {
        Result<Map<String, Object>> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        Discuss discuss = new Discuss();
        discuss.setArticleId(articleId);
        discuss.setContent(content);
        discuss.setDiscussTime(new Date().getTime());
        discuss.setUserBaseInfo(new UserBaseInfo(subject.getPrincipal().toString()));
        Map<String, Object> map = new HashMap<>();
        map.put("discussId", discussService.writeDiscuss(discuss));
        map.put("userBaseInfo", userBaseInfoService.getUserBaseInfoById(subject.getPrincipal().toString()));
        return result.success(map);
    }


    @RequestMapping(value = "replyComment.do", method = RequestMethod.POST)
    @ApiOperation(value = "回复主评论人(评论文章的)的评论", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "discussId", value = "回复评论的主键", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = true, dataType = "String")

    })
    @ResponseBody
    public Result<Map<String, Object>> replyComment(String discussId, String content) {
        Result<Map<String, Object>> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        ReplyDiscuss replyDiscuss = new ReplyDiscuss();
        replyDiscuss.setDiscussId(discussId);
        replyDiscuss.setContent(content);
        replyDiscuss.setDiscussTime(new Date().getTime());
        replyDiscuss.setUserBaseInfo(new UserBaseInfo(subject.getPrincipal().toString()));
        Map<String, Object> map = new HashMap<>();
        map.put("replyDiscussId", discussService.writeReplyDiscuss(replyDiscuss));
        map.put("userBaseInfo", userBaseInfoService.getUserBaseInfoById(subject.getPrincipal().toString()));
        return result.success(map);
    }


    @ApiOperation(value = "子评论人(评论文章的评论人下的评论人)相互之间的评论", httpMethod = "POST")
    @RequestMapping(value = "toReplyDiscuss.do")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "discussId", value = "主评论人的的序号", required = true),
            @ApiImplicitParam(name = "targetUserMwId", value = "子评论人的mwId", required = true),
            @ApiImplicitParam(name = "toContent", value = "回复目标人的内容", required = true),
            @ApiImplicitParam(name = "content", value = "评论的内容", required = true)
    })
    @ResponseBody
    public Result<Map<String, Object>> toReplyDiscuss(String discussId, String targetUserMwId, String toContent, String content) {
        Result<Map<String, Object>> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        ReplyDiscuss replyDiscuss = new ReplyDiscuss();
        replyDiscuss.setDiscussId(discussId);
        replyDiscuss.setTargetUserBaseInfo(new UserBaseInfo(targetUserMwId));
        replyDiscuss.setToContent(toContent);
        replyDiscuss.setContent(content);
        replyDiscuss.setDiscussTime(new Date().getTime());
        replyDiscuss.setUserBaseInfo(new UserBaseInfo(subject.getPrincipal().toString()));
        Map<String, Object> map = new HashMap<>();
        map.put("replyDiscussId", discussService.writeReplyDiscuss(replyDiscuss));
        map.put("userBaseInfo", userBaseInfoService.getUserBaseInfoById(subject.getPrincipal().toString()));
        map.put("targetUserBaseInfo", userBaseInfoService.getUserBaseInfoById(targetUserMwId));
        return result.success(map);
    }

    @RequestMapping(value = "/updateDiscussPraise.do")
    @ApiOperation(value = "更新评论者的赞数", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "discussId", value = "评论的Id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "isIncrement", value = "评论是否点赞 true 赞数加一", required = true, dataType = "Boolean")
    })
    @ResponseBody
    public Result<Integer> updateDiscussPraise(String discussId, boolean isIncrement) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result<Integer>().failure(ResultCode.UnLogin);
        }
        articleService.updateDiscussPraise(discussId, isIncrement);
        return new Result<Integer>().success();
    }

    @ApiOperation(value = "更新回复评论者的赞数", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "replyDiscussId", value = "回复评论的Id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "isIncrement", value = "回复评论是否点赞 true 赞数加一", required = true, dataType = "Boolean")
    })
    @RequestMapping(value = "/updateReplyDiscussPraise.do")
    @ResponseBody
    public Result<Integer> updateReplyDiscussPraise(String replyDiscussId, boolean isIncrement) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result<Integer>().failure(ResultCode.UnLogin);
        }
        articleService.updateReplyPraise(replyDiscussId, isIncrement);
        return new Result<Integer>().success();
    }
}
