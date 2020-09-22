package com.bupt.dql.web.controller;

import com.bupt.dql.service.LoginService;
import com.bupt.dql.web.exception.ParamException;
import com.bupt.dql.web.pojo.dto.ResultDTO;
import com.bupt.dql.web.pojo.http.request.HttpLoginRequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultDTO login(@RequestBody HttpLoginRequestParam param, HttpServletRequest request) throws ParamException {
        System.out.println(param);
        loginService.login(param, request);
        return ResultDTO.success();
    }

}
