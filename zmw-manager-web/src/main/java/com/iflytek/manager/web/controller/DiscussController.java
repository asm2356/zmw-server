package com.iflytek.manager.web.controller;

import com.iflytek.common.model.Discuss;
import com.iflytek.common.model.ReplyDiscuss;
import com.iflytek.common.model.Result;
import com.iflytek.manager.api.DiscussService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@RestController
@RequestMapping(value = "discuss")
public class DiscussController {

    @Autowired
    private DiscussService discussService;

    @RequiresRoles(value = {"admin", "superAdmin"},logical = Logical.OR)
    @RequestMapping(value = "/getOnlyDiscussList.do")
    public Result<List<Discuss>> getOnlyDiscussList(String discussId,
                                                    String articleId,
                                                    String content,
                                                    String mwId,
                                                    long startNum,
                                                    int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("discussId", discussId);
        map.put("articleId", articleId);
        map.put("content", content);
        map.put("mwId", mwId);
        map.put("startNum", startNum);
        map.put("pageSize", pageSize);
        return new Result<List<Discuss>>().success(discussService.getOnlyDiscussList(map));
    }

    @RequiresRoles(value = {"admin", "superAdmin"},logical = Logical.OR)
    @RequestMapping(value = "/getOnlyDiscussCount.do")
    public Result<Integer> getOnlyDiscussCount(String discussId,
                                               String articleId,
                                               String content,
                                               String mwId,
                                               long startNum,
                                               int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("discussId", discussId);
        map.put("articleId", articleId);
        map.put("content", content);
        map.put("mwId", mwId);
        map.put("startNum", startNum);
        map.put("pageSize", pageSize);
        return new Result<Integer>().success(discussService.getOnlyDiscussCount(map));
    }

    @RequiresRoles(value = {"admin", "superAdmin"},logical = Logical.OR)
    @RequestMapping(value = "deleteDiscuss.do")
    @RequiresPermissions(value = "deleteDiscuss")
    public Result<Integer> deleteDiscuss(String discussId) {
        return new Result<Integer>().success(discussService.deleteDiscuss(discussId));
    }

    @RequiresRoles(value = {"admin", "superAdmin"},logical = Logical.OR)
    @RequestMapping(value = "/getReplyDiscussList.do")
    public Result<List<ReplyDiscuss>> getReplyDiscussList(String replyDiscussId,
                                                          String discussId,
                                                          String mwId,
                                                          String toContent,
                                                          String replyContent,
                                                          long startNum,
                                                          int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("replyDiscussId", replyDiscussId);
        map.put("discussId", discussId);
        map.put("mwId", mwId);
        map.put("toContent", toContent);
        map.put("replyContent", replyContent);
        map.put("startNum", startNum);
        map.put("pageSize", pageSize);
        return new Result<List<ReplyDiscuss>>().success(discussService.getReplyDiscussList(map));
    }

    @RequiresRoles(value = {"admin", "superAdmin"},logical = Logical.OR)
    @RequestMapping(value = "/getReplyDiscussCount.do")
    public Result<Integer> getReplyDiscussCount(String replyDiscussId,
                                                String discussId,
                                                String mwId,
                                                String toContent,
                                                String replyContent,
                                                long startNum,
                                                int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("replyDiscussId", replyDiscussId);
        map.put("discussId", discussId);
        map.put("mwId", mwId);
        map.put("toContent", toContent);
        map.put("replyContent", replyContent);
        map.put("startNum", startNum);
        map.put("pageSize", pageSize);
        return new Result<Integer>().success(discussService.getReplyDiscussCount(map));
    }

    @RequiresRoles(value = {"admin", "superAdmin"},logical = Logical.OR)
    @RequestMapping(value = "/deleteReplyDiscuss.do")
    @RequiresPermissions(value = "deleteReplyDiscuss")
    public Result<Integer> deleteReplyDiscuss(String replyDiscussId) {
        return new Result<Integer>().success(discussService.deleteReplyDiscuss(replyDiscussId));
    }
}
