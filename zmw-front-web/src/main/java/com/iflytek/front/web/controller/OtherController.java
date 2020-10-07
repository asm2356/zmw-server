package com.iflytek.front.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author AZhao
 */
@Controller
public class OtherController {
    @RequestMapping(value = "notFound")
    public String notFound() {
        return "error/notFound";
    }

    @RequestMapping(value = "serverError")
    public String serverError() {
        return "error/serverError";
    }
}
