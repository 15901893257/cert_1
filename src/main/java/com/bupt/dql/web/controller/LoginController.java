package com.bupt.dql.web.controller;

import com.bupt.dql.service.LoginService;
import com.bupt.dql.web.exception.ParamException;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.dto.LoginDTO;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     *  登录首页
     *
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 系统登录
     *
     * @param loginDTO 登录参数
     * @param request  网络请求
     * @return
     * @throws ParamException
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, Model model) throws ParamException {
        System.out.println(loginDTO);
        SysUserDO sysUserDO = loginService.login(loginDTO, request);
        model.addAttribute("user", sysUserDO);
        return JsonResult.success();
    }

    /**
     *  系统首页
     * @return
     */
    @GetMapping("/success")
    public String success(){
        return "index";
    }

}
