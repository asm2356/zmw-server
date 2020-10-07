package com.iflytek.manager.web.controller;

import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.exception.DeleteFileException;
import com.iflytek.common.exception.PageException;
import com.iflytek.common.model.Article;
import com.iflytek.common.model.Result;
import com.iflytek.manager.api.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@RestController
@RequestMapping(value = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @OpLog(describe = "获取文章列表")
    @ApiOperation(value = "获取文章列表(公开度:所有)", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"})
    @RequestMapping(value = "getArticleList.do")
    public Result<List<Article>> getArticleList(@RequestParam(value = "startNum") Long startNum,
                                                @RequestParam(value = "pageSize") Integer pageSize,
                                                @RequestParam(value = "mwId", required = false) String mwId,
                                                @RequestParam(value = "startTime", required = false) Long startTime,
                                                @RequestParam(value = "endTime", required = false) Long endTime,
                                                @RequestParam(value = "title", required = false) String title) throws PageException {
        Result<List<Article>> result = new Result<>();
        Map<String, Object> map = new HashMap<>();
        map.put("startNum", startNum);
        map.put("pageSize", pageSize);
        map.put("mwId", mwId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("title", title);
        return result.success(articleService.getArticleList(map));
    }

    @ApiOperation(value = "获取文章数量(公开度:所有)", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"})
    @RequestMapping(value = "getArticleNumber.do")
    public Result<Integer> getArticleNumber(@RequestParam(value = "mwId", required = false) String mwId,
                                            @RequestParam(value = "startTime", required = false) Long startTime,
                                            @RequestParam(value = "endTime", required = false) Long endTime,
                                            @RequestParam(value = "title", required = false) String title) {
        Result<Integer> result = new Result<>();
        Map<String, Object> map = new HashMap<>();
        map.put("mwId", mwId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("title", title);
        return result.success(articleService.getArticleNumber(map));
    }

    @OpLog(describe = "获取文章")
    @ApiOperation(value = "获取文章(公开度:所有)", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"})
    @RequestMapping(value = "getArticle.do")
    public Result<Article> getArticle(String articleId) {
        Result<Article> result = new Result<>();
        return result.success(articleService.getArticle(articleId));
    }

    @OpLog(describe = "删除文章")
    @ApiOperation(value = "删除文章(公开度:所有)", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequiresPermissions(value = "deleteArticle")
    @RequestMapping(value = "deleteArticle.do")
    public Result<Integer> deleteArticle(String articleId) throws DeleteFileException {
        Result<Integer> result = new Result<>();
        return result.success(articleService.deleteArticle(articleId));
    }
}
