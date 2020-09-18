package com.bupt.dql.web.controller.sys;

import com.bupt.dql.web.exception.ParamException;
import com.bupt.dql.web.pojo.SysUserDO;
import com.bupt.dql.web.pojo.dto.ResultDTO;
import com.bupt.dql.web.pojo.http.request.HttpLoginRequestParam;
import com.bupt.dql.service.SysUserService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultDTO login(@RequestBody HttpLoginRequestParam param, HttpServletRequest request) throws ParamException {
        System.out.println(param);
        sysUserService.login(param, request);
        return ResultDTO.success();
    }

    @RequestMapping("/loginDo")
//    @ResponseBody
    public String loginDo(SysUserDO sysUserDO, HttpServletRequest request, HttpSession session, RedirectAttributes model) {
//        model.addFlashAttribute("msg","");

        String verCode = request.getParameter("verCode");
//        System.out.println(verCode);
        //验证码为空，验证码不一致，验证码超时
        verCode = verCode.toLowerCase();
        String tempVerCode = (String) session.getAttribute("verCode");
        Long tempVerCodeTime = (Long) session.getAttribute("verCodeTime");
        String msg = "";
        model.addFlashAttribute("username", sysUserDO.getUsername());

        model.addFlashAttribute("password", sysUserDO.getPassword());
        if (verCode == null || !verCode.equals(tempVerCode)) {
            msg = "验证码错误";
            model.addFlashAttribute("msg",msg);
            return "redirect:/index/login";
        }
        if (System.currentTimeMillis() - tempVerCodeTime > 120000) {//设置验证码2分钟内失效\
            msg = "验证码失效";
            model.addFlashAttribute("msg",msg);
            return "redirect:/index/login";
        }
        SysUserDO sysUserDO1 = sysUserService.getSysUser(sysUserDO.getUsername());
        if (sysUserDO1 != null){
            //session和request.getSession()都可以，用法一样
            session.setAttribute("user", sysUserDO1);
//            request.getSession().setAttribute("user",sysUserDO1);
            msg = "登录成功";
//            model.addFlashAttribute("user",sysUserDO1);
//            return "sys/success";
            return "sys/project/projectPageList";
        }
        msg = "用户名或密码错误";
        model.addFlashAttribute("msg",msg);
        return "redirect:/index/login";
    }

    /**
     * 使用ajax验证登录
     * @param objectSysUser
     * @param verCode
     * @param session
     * @return
     */
    @RequestMapping("loginDo1")
    @ResponseBody
    public String loginDo1(@RequestParam String objectSysUser, @RequestParam String verCode,
                           HttpSession session,HttpServletRequest request){
        //student转换成json对象，再转成java对象
        JSONObject jsonObject = JSONObject.fromObject(objectSysUser);
        SysUserDO sysUserDO = (SysUserDO) jsonObject.toBean(jsonObject, SysUserDO.class);
        verCode = verCode.toLowerCase();
        String tempVerCode = (String) session.getAttribute("verCode");
        Long tempVerCodeTime = (Long) session.getAttribute("verCodeTime");
        String msg = "";

        if (verCode == null || !verCode.equals(tempVerCode)) {
            msg = "验证码错误";
        }else if (System.currentTimeMillis() - tempVerCodeTime > 120000) {//设置验证码2分钟内失效\
            msg = "验证码失效";
        }else {
            SysUserDO sysUserDO1 = sysUserService.getSysUser(sysUserDO.getUsername());
            if (sysUserDO1 != null){
                request.getSession().setAttribute("user", sysUserDO1);
                msg = "登录成功";
            }else {
                msg = "用户名或密码错误";
            }
        }
        return msg;
    }
}
