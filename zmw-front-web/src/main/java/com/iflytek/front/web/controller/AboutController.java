package com.iflytek.front.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author AZhao
 */
@Controller
public class AboutController {
    @RequestMapping(value = "about")
    public String about() {
        return "page/about";
    }
}
