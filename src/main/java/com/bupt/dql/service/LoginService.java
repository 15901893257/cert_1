package com.bupt.dql.service;

import com.bupt.dql.common.util.MD5Util;
import com.bupt.dql.constant.GlobalConstant;
import com.bupt.dql.web.enums.SysUserStatusEnum;
import com.bupt.dql.web.exception.ParamException;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Service
@Slf4j
public class LoginService {

    @Resource
    private ISysUserService sysUserService;

    public SysUserDO login(LoginDTO loginDTO, HttpServletRequest request) throws ParamException {
        checkLoginParam(loginDTO);
        //校验验证码
        HttpSession session = request.getSession();
        String tempVerCode = (String) session.getAttribute("verCode");
        System.out.println("验证码：" + tempVerCode);
        Long tempVerCodeTime = (Long) session.getAttribute("verCodeTime");
        Long curTime = System.currentTimeMillis();
        if (!tempVerCode.equals(loginDTO.getVerCode())) {
            log.info("验证码错误！");
            throw new ParamException("验证码错误！");
        }
        if (curTime - tempVerCodeTime > GlobalConstant.VERCODE_TIMEOUT) {
            log.info("验证码过期，请刷新！");
            throw new ParamException("验证码过期，请刷新！");
        }

        //登录用户校验
        SysUserDO sysUserDO = sysUserService.querySysUserByUserName(loginDTO.getUsername());
        if (null == sysUserDO) {
            log.info("该用户没有注册！");
            throw new ParamException("该用户没有注册！");
        }
        if (!sysUserDO.getPassword().equals(MD5Util.encrypt(loginDTO.getPassword()))) {
            log.info("用户名密码错误！");
            throw new ParamException("用户名密码错误！");
        }
        if (sysUserDO.getStatus() == SysUserStatusEnum.INVALID.getCode()) {
            log.info("该用户已被禁用");
            throw new ParamException("该用户已被禁用");
        }
        return sysUserDO;
    }

    private void checkLoginParam(LoginDTO loginDTO) throws ParamException {
        if (loginDTO == null) {
            log.error(GlobalConstant.PARAM_ERROR);
            throw new ParamException(GlobalConstant.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(loginDTO.getUsername()) || StringUtils.isEmpty(loginDTO.getPassword())) {
            log.info("用户名或密码不能为空！");
            throw new ParamException("用户名或密码不能为空！");
        }
        if (StringUtils.isEmpty(loginDTO.getVerCode())) {
            log.info("验证码不能为空！");
            throw new ParamException("验证码不能为空！");
        }
    }
}
