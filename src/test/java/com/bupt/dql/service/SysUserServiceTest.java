package com.bupt.dql.service;

import com.bupt.dql.TestBase;
import com.bupt.dql.common.util.DateUtil;
import com.bupt.dql.common.util.MD5Util;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.query.SysUserQuery;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: mai
 * @date: 2020/9/22
 */
public class SysUserServiceTest extends TestBase {

    @Resource
    private ISysUserService sysUserService;

    @Test
    public void test01(){
        SysUserDO sysUserDO = sysUserService.querySysUserByUserName("dql");
        if (sysUserDO != null) {
            System.out.println(sysUserDO);
        } else {
            System.out.println("没有该用户");
        }

    }

    @Test
    public void test02(){
        SysUserQuery query = new SysUserQuery();
        //query.setKeywords("邓权亮");
        List<SysUserDO> sysUserDOList = sysUserService.queryUserList(query);

        if (CollectionUtils.isEmpty(sysUserDOList)) {
            System.out.println("list为空");
        } else {
            sysUserDOList.forEach(System.out :: println);
        }
    }

    @Test
    public void test03() throws ParseException {
        long curTime = System.currentTimeMillis();
        String birth = "1996-09-23";
        String operator = "邓权亮";
        String password = "123456";
        List<SysUserDO> sysUserDOList = new ArrayList<>();
        for(int i = 1021; i <= 1030; i++) {
            SysUserDO sysUserDO = new SysUserDO();
            sysUserDO.setUserId(Long.valueOf(i));
            sysUserDO.setName("杨康");
            sysUserDO.setUsername("mai" + i);
            sysUserDO.setPassword(MD5Util.encrypt(password));
            sysUserDO.setGender(i % 2);
            sysUserDO.setPhone("18810165025");
            sysUserDO.setEmail("1372565062@qq.com");
            sysUserDO.setBirthDay(DateUtil.stringToDate(birth).getTime());
            sysUserDO.setLoginIp("127.0.0.1");
            sysUserDO.setLoginTime(curTime);
            sysUserDO.setStatus(i % 2);
            sysUserDO.setUType(i % 2);
            sysUserDO.setAvatar("");
            sysUserDO.setCtime(curTime);
            sysUserDO.setUtime(curTime);
            sysUserDO.setOperator(operator);
            sysUserDO.setRemark("");
            sysUserDOList.add(sysUserDO);
        }
        //批量char
        sysUserService.batchInsert(sysUserDOList);
        //插入
        //sysUserDOList.forEach(e -> sysUserService.insertOrUpdate(e));
    }

    @Test
    public void test04() throws ParseException {
        long curTime = System.currentTimeMillis();
        int i = 1004;
        String birth = "1995-08-23";
        String operator = "邓权亮";
        String password = "123456";
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setUserId(Long.valueOf(i));
        sysUserDO.setName("郭靖");
        sysUserDO.setUsername("mai" + i);
        sysUserDO.setPassword(MD5Util.encrypt(password));
        sysUserDO.setGender(i % 2);
        sysUserDO.setPhone("15901893257");
        sysUserDO.setEmail("1372565062@qq.com");
        sysUserDO.setBirthDay(DateUtil.stringToDate(birth).getTime());
        sysUserDO.setLoginIp("127.0.0.1");
        sysUserDO.setLoginTime(curTime);
        sysUserDO.setStatus(i % 2);
        sysUserDO.setUType(i % 2);
        sysUserDO.setAvatar("");
        sysUserDO.setCtime(curTime);
        sysUserDO.setUtime(curTime);
        sysUserDO.setOperator(operator);
        sysUserDO.setRemark("");
        System.out.println(sysUserService.insertOrUpdate(sysUserDO));
    }

    @Test
    public void test05() throws ParseException {
        SysUserDO sysUserDO = sysUserService.querySysUserByUserName("mai");
        long curTime = System.currentTimeMillis();
        String birth = "1995-08-23";
        sysUserDO.setBirthDay(DateUtil.stringToDate(birth).getTime());
        sysUserDO.setLoginTime(curTime);
        sysUserDO.setCtime(curTime);
        sysUserDO.setUtime(curTime);
        sysUserDO.setLoginIp("127.0.0.1");
        sysUserService.insertOrUpdate(sysUserDO);
    }
}
