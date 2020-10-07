package com.iflytek.front.web.controller;

import com.iflytek.common.enumeration.user.Openness;
import com.iflytek.front.web.service.HomeService;
import com.iflytek.manager.api.*;
import com.iflytek.search.api.SearchArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
/**
 * @author AZhao
 */
@Controller
public class HomeController {
    @Autowired
    private SearchArticleService searchArticleService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/home")
    public String home(@RequestParam(value = "startNum", required = false, defaultValue = "0") int startNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                       @RequestParam(value = "categoryName", required = false) String categoryName,
                       Model model) {
        model.addAttribute("articleList", homeService.getArticleList(startNum, pageSize, categoryName));
        model.addAttribute("hotArticleList", homeService.getHotArticleList());
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", categoryName);
        map.put("openness", Openness.Open.getValue());
        model.addAttribute("articleCount", articleService.getArticleNumber(map));
        return "page/homeDetail";
    }

    @ApiOperation(value = "文章标题搜索")
    @RequestMapping(value = "/searchHome")
    public String searchHome(String title, Model model) {
        // TODO: 2019/6/2  分页  评论量
        model.addAttribute("hotArticleList", homeService.getHotArticleList());
        model.addAttribute("articleList", searchArticleService.getArticleListByTitle(title));
        model.addAttribute("articleCount", searchArticleService.getArticleNumberByTitle(title));
        return "page/homeDetail";
    }

}
