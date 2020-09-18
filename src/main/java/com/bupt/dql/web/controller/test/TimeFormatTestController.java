package com.bupt.dql.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author: mai
 * @date: 2019/9/24
 */
@RequestMapping("test")
@Controller
public class TimeFormatTestController {

    @RequestMapping("time")
    @ResponseBody
    public String testTimeFormat(){
        Date date = new Date();
        System.err.println("{spring.jackson.date-format}");
        System.err.println(date);
        return "s";
    }
}
