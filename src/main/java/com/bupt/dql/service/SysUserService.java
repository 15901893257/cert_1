package com.bupt.dql.service;

import com.bupt.dql.common.util.MD5Util;
import com.bupt.dql.constant.GlobalConstant;
import com.bupt.dql.dao.SysUserMapper;
import com.bupt.dql.web.exception.ParamException;
import com.bupt.dql.web.pojo.SysUserDO;
import com.bupt.dql.web.pojo.http.request.HttpLoginRequestParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: mai
 * @date: 2020/9/14
 */
@Slf4j
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void login(HttpLoginRequestParam param, HttpServletRequest request) throws ParamException {
        checkLoginParam(param);
        SysUserDO sysUserDO = sysUserMapper.getSysUser(param.getUsername());
        if (null == sysUserDO) {
            log.info("该用户没有注册！");
            throw new ParamException("该用户没有注册！");
        }
        if (!sysUserDO.getPassword().equals(MD5Util.encrypt(param.getPassword()))) {
            log.info("密码错误！");
            throw new ParamException("密码错误！");
        }
        HttpSession session = request.getSession();
        String tempVerCode = (String) session.getAttribute("verCode");
        System.out.println("验证码：" + tempVerCode);
        Long tempVerCodeTime = (Long) session.getAttribute("verCodeTime");
        Long curTime = System.currentTimeMillis();
        if (!tempVerCode.equals(param.getVerCode())) {
            log.info("验证码错误！");
            throw new ParamException("验证码错误！");
        }
        if (curTime - tempVerCodeTime > GlobalConstant.VERCODE_TIMEOUT) {
            log.info("验证码过期，请刷新！");
            throw new ParamException("验证码过期，请刷新！");
        }
       // return "sys/project/projectPageList";
    }
    public SysUserDO getSysUser(String username){
        SysUserDO sysUserDO = sysUserMapper.getSysUser(username);
        if(sysUserDO == null){
            return null;
        }
        return sysUserDO;
    }

    private void checkLoginParam(HttpLoginRequestParam param) throws ParamException {
        if (param == null) {
            log.error(GlobalConstant.PARAM_ERROR);
            throw new ParamException(GlobalConstant.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(param.getUsername()) || StringUtils.isEmpty(param.getPassword())) {
            log.info("用户名或密码不能为空！");
            throw new ParamException("用户名或密码不能为空！");
        }
        if (StringUtils.isEmpty(param.getVerCode())) {
            log.info("验证码不能为空！");
            throw new ParamException("验证码不能为空！");
        }
    }

}
