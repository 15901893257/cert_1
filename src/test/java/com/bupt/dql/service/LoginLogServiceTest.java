package com.bupt.dql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;

import com.bupt.dql.TestBase;
import com.bupt.dql.web.enums.LoginStatusEnum;
import com.bupt.dql.web.pojo.entity.LoginLogDO;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
public class LoginLogServiceTest extends TestBase {

    @Resource
    private ILoginLogService loginLogService;

    @Test
    public void testBatchInsert() {
        List<LoginLogDO> loginLogDOList = new ArrayList<>();
        Random random = new Random();
        for (int i = 1030; i < 1050; i++) {
            LoginLogDO loginLogDO = LoginLogDO.builder()
                    .loginIp("127.0.0.1")
                    .browser("")
                    .username("邓权亮")
                    .userId(Long.valueOf(i))
                    .result(random.nextInt(2))
                    .loginType(random.nextInt(2))
                    .loginTime(System.currentTimeMillis())
                    .build();
            loginLogDOList.add(loginLogDO);
        }
        loginLogService.batchInsert(loginLogDOList);
    }
}
