package com.bupt.dql.web.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.dql.common.util.DateUtil;
import com.bupt.dql.common.util.MD5Util;
import com.bupt.dql.service.ISysUserService;
import com.bupt.dql.web.common.BaseController;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.vo.SysUserVO;
import com.bupt.dql.web.query.SysUserQuery;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Slf4j
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService sysUserService;

    @PostMapping("/list")
    @ResponseBody
    public JsonResult list(SysUserQuery query) {
        return sysUserService.getList(query);
    }

    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody SysUserVO sysUserVO, HttpServletRequest request) throws ParseException {
        log.info("注册用户，sysUserVO: {}", sysUserVO);
        //存入会话session
        HttpSession session = request.getSession();
        SysUserDO sysUserDO = new SysUserDO();
        BeanUtils.copyProperties(sysUserVO, sysUserDO, "password");

        sysUserDO.setUserId(sysUserService.getUserId());
        sysUserDO.setPassword(MD5Util.encrypt(sysUserVO.getPassword()));
        sysUserDO.setOperator((String) session.getAttribute("username"));
        sysUserDO.setUpdateOperator((String) session.getAttribute("username"));
        sysUserDO.setLoginIp("127.0.0.1");
        long curTime = System.currentTimeMillis();
        sysUserDO.setUtime(curTime);
        sysUserDO.setCtime(curTime);
        sysUserDO.setUType(0);
        sysUserDO.setBirthDay(DateUtil.stringToLong(sysUserVO.getBirthDay()));

        sysUserService.insertOrUpdate(sysUserDO);
        return JsonResult.success("新增用户成功");
    }
//    @GetMapping("/index")
//    public String index(){
//        return "admin/index";
//    }
}
