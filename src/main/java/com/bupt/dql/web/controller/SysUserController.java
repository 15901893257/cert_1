package com.bupt.dql.web.controller;

import com.bupt.dql.service.ISysUserService;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.query.SysUserQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;

    @PostMapping("/list")
    @ResponseBody
    public JsonResult list(SysUserQuery query){
        return sysUserService.getList(query);
    }

    @GetMapping("/index")
    public String index(){
        return "admin/index1";
    }
}
