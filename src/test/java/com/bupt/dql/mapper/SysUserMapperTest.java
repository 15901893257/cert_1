package com.bupt.dql.mapper;

import com.bupt.dql.TestBase;
import com.bupt.dql.dao.SysUserMapper;
import com.bupt.dql.web.pojo.SysUserDO;
import org.junit.Test;
import javax.annotation.Resource;

/**
 * @author: mai
 * @date: 2020/9/14
 */
public class SysUserMapperTest extends TestBase {

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    public void test01(){
        String username = "admin";
        if (sysUserMapper == null) {
            System.out.println("null");
            return;
        }
        SysUserDO sysUserDO = sysUserMapper.getSysUser(username);
        System.out.println(sysUserDO);
    }
}
