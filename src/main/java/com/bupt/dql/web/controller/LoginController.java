package com.bupt.dql.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.dql.common.annotation.Log;
import com.bupt.dql.service.LoginService;
import com.bupt.dql.web.LoginLogConstant;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.exception.ParamException;
import com.bupt.dql.web.pojo.dto.LoginDTO;
import com.bupt.dql.web.pojo.entity.SysUserDO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Slf4j
@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
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
    @Log(value = LoginLogConstant.LOGIN)
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, Model model) throws ParamException {
        log.info("登录用户，loginDTO: {}", loginDTO);
        SysUserDO sysUserDO = loginService.login(loginDTO, request);
        model.addAttribute("user", sysUserDO);
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("username");
        session.setAttribute("username", loginDTO.getUsername());
        return JsonResult.success();
    }

    /**
     *  系统首页
     * @return
     */
    @GetMapping("/success")
    public String success() {
        log.info("success");
        return "index";
    }

    /**
     *  注册页面
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 退出退出系统
     * @return
     */
    @Log(value = LoginLogConstant.LOGOUT)
    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
