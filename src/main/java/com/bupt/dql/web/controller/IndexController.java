package com.bupt.dql.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/index")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/login1")
    public String index(){
        return "index";
    }

    /**
     * 使用ajax验证登录
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     *
     * ajax验证成功，跳转至这里
     * 跳转至系统首页
     * @return
     */
    @RequestMapping("/success")
    public String success(){
        logger.info("success");
        return "es/search";
    }

    /**
     * 登录注销
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "index";
    }
    @RequestMapping("/test")
    public String test(){
        logger.info("we");
        return "sys/success";
    }

}
