package com.iflytek.front.web.controller;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.constant.ConfigConstant;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.enumeration.user.LetterStatus;
import com.iflytek.common.model.Letter;
import com.iflytek.common.model.Result;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.manager.api.LetterService;
import com.iflytek.manager.api.UserBaseInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @author AZhao
 */
@Controller
@RequestMapping(value = "/chat")
public class ChatController {
    @Autowired
    private LetterService letterService;

    @Autowired
    private UserBaseInfoService userBaseInfoService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @ApiOperation(value = "获取私信列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startNum", value = "开始数"),
            @ApiImplicitParam(name = "pageSize", value = "页大小"),
            @ApiImplicitParam(name = "readStatus", value = "当前阅读状态，已读和未读"),
            @ApiImplicitParam(name = "bothStatus", value = "是否获取双方的私信")
    })
    @RequestMapping(value = "/getLetterList")
    public String getLetterList(@RequestParam(value = "startNum", defaultValue = "0") long startNum,
                                @RequestParam(value = "pageSize", defaultValue = "6") int pageSize,
                                @RequestParam(value = "readStatus", defaultValue = "1") int readStatus,
                                @RequestParam(value = "readStatus", defaultValue = "false") boolean bothStatus,
                                Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return "/page/user/login";
        }
        List<Letter> letterList = letterService.getLetterList(startNum, pageSize, subject.getPrincipal().toString(), readStatus, bothStatus);
        model.addAttribute("letterList", letterList);
        return "page/letter/letterList";
    }


    @ApiOperation(value = "删除某条私信", httpMethod = "POST")
    @RequestMapping(value = "deleteLetter.do")
    @ResponseBody
    public Result<Integer> deleteLetter(String id) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result<>(ResultCode.UnLogin);
        }
        return new Result<Integer>().success(letterService.deleteLetter(id));
    }

    @ApiOperation(value = "写私信", httpMethod = "POST")
    @RequestMapping(value = "writeLetter.do")
    @ResponseBody
    public Result<Letter> writeLetter(String letterJson) {
        Result<Letter> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        Letter letter = JSON.parseObject(letterJson, Letter.class);
        letter.setId(RandomUtils.getUUID());
        UserBaseInfo toUserBaseInfo = userBaseInfoService.getUserBaseInfoById(letter.getToUserBaseInfo().getMwId());
        if(toUserBaseInfo==null){
            return result.failure(ResultCode.Unknown_User);
        }
        UserBaseInfo userBaseInfo = userBaseInfoService.getUserBaseInfoById(subject.getPrincipal().toString());
        letter.setToUserBaseInfo(toUserBaseInfo);
        letter.setUserBaseInfo(userBaseInfo);
        jmsTemplate.convertAndSend(ConfigConstant.CHAT_QUEUE, letter);
        return result.success(letter);
    }

    @ApiOperation(value = "阅读私信，阅读之后标记已读", httpMethod = "POST")
    @RequestMapping(value = "readLetter.do")
    @ResponseBody
    public Result<Integer> readLetter(String id) {
        Result<Integer> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        return result.success(letterService.readLetter(id));
    }

    @RequestMapping(value = "getAllLetter")
    public String getAllLetter(@RequestParam(value = "startNum", defaultValue = "0") long startNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return "page/user/login";
        }
        List<Letter> letterList = letterService.getLetterList(startNum, pageSize, subject.getPrincipal().toString(), LetterStatus.All.getValue(), true);
        UserBaseInfo userBaseInfo = userBaseInfoService.getUserBaseInfoById(subject.getPrincipal().toString());
        model.addAttribute("letterList", letterList);
        model.addAttribute("currentUser", userBaseInfo);
        model.addAttribute("letterNumber", letterService.getLetterNumber(subject.getPrincipal().toString(), true));
        return "page/letter/allLetter";
    }
}
