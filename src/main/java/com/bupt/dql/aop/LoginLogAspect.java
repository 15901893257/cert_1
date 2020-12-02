package com.bupt.dql.aop;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.bupt.dql.common.annotation.Log;
import com.bupt.dql.service.ILoginLogService;
import com.bupt.dql.web.enums.LoginStatusEnum;
import com.bupt.dql.web.enums.LoginTypeEnum;
import com.bupt.dql.web.pojo.entity.LoginLogDO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
@Slf4j
@Aspect
@Component
public class LoginLogAspect {

    @Resource
    private ILoginLogService loginLogService;

    //定义切点
    @Pointcut("@annotation(com.bupt.dql.common.annotation.Log)")
    public void pointCut() {
    }

    @SuppressWarnings("checkstyle:RegexpSingleline")
    @AfterReturning("pointCut()")
    public void insertLog(JoinPoint joinPoint) {
        log.info("loginLogAspect afterReturning....");

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        log.info("切入点：{}", method.getName());

        Log log = method.getAnnotation(Log.class);
        if (log == null) {
            return;
        }
        String login = log.value();
        int loginType = LoginTypeEnum.LOGIN.getName().equals(login)
                ? LoginTypeEnum.LOGIN.getCode() : LoginTypeEnum.LOGOUT.getCode();
        LoginLogDO loginLogDO = LoginLogDO.builder()
                .loginIp("127.0.0.1")
                .browser("")
                .username("邓权亮")
                .userId(1024L)
                .result(LoginStatusEnum.SUCCESS.getCode())
                .loginType(loginType)
                .loginTime(System.currentTimeMillis())
                .build();
        loginLogService.insertOrUpdate(loginLogDO);
    }
}
