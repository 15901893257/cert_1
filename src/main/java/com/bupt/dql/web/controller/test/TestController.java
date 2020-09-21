package com.bupt.dql.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: mai
 * @date: 2020/9/18
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    public String index(){
        return "sys/index";
    }

    @RequestMapping("/test")
    public String test(){
        return "sys/test";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
