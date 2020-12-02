package com.bupt.dql.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.dql.TestBase;
import com.bupt.dql.common.util.DateUtil;
import com.bupt.dql.common.util.MD5Util;
import com.bupt.dql.dao.SysUserMapper;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.vo.SysUserVO;
import com.bupt.dql.web.query.SysUserQuery;
import com.google.common.collect.Lists;

/**
 * @author: mai
 * @date: 2020/9/22
 */
public class SysUserServiceTest extends TestBase {

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private SysUserMapper sysUserMapper;


    @Test
    public void testDeleteById() {
        long id1 = 1L;  //存在
        long id2 = 100L;  //不存在
        int a = sysUserService.deleteById(id1);
        int b = sysUserService.deleteById(id2);
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void testBatchDelete() {
        List<Long> ids = Lists.newArrayList(2L, 3L, 4L, 40L);
        int a = sysUserService.batchDelete(ids);
        System.out.println(a);
    }

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
    public void testQueryById() {
        SysUserDO sysUserDO1 = sysUserService.queryById(1L);
        SysUserDO sysUserDO2 = sysUserService.queryById(2L);
        System.out.println(sysUserDO1);
        System.out.println(sysUserDO2);
    }

    @Test
    public void test03() throws ParseException {
        long curTime = System.currentTimeMillis();
        String birth = "1996-01-23";
        String operator = "mai";
        String password = "123456";
        List<SysUserDO> sysUserDOList = new ArrayList<>();
        for(int i = 1041; i <= 1050; i++) {
            SysUserDO sysUserDO = new SysUserDO();
            sysUserDO.setUserId(Long.valueOf(i));
            sysUserDO.setName("李白");
            sysUserDO.setUsername("libai" + i);
            sysUserDO.setPassword(MD5Util.encrypt(password));
            sysUserDO.setGender((i + 1) % 2);
            sysUserDO.setPhone("15901893257");
            sysUserDO.setEmail("1985529368@qq.com");
            sysUserDO.setBirthDay(DateUtil.stringToDate(birth).getTime());
            sysUserDO.setLoginIp("127.0.0.1");
            sysUserDO.setLoginTime(curTime);
            sysUserDO.setStatus(i % 2);
            sysUserDO.setUType(i % 2);
            sysUserDO.setAvatar("");
            sysUserDO.setCtime(curTime);
            sysUserDO.setUtime(curTime);
            sysUserDO.setOperator(operator);
            sysUserDO.setRemark("测试数据");
            sysUserDO.setUpdateOperator(operator);
            sysUserDOList.add(sysUserDO);
        }
        //批量
        sysUserService.batchInsert(sysUserDOList);
        //插入
        //sysUserDOList.forEach(e -> sysUserService.insertOrUpdate(e));
    }

    @Test
    public void test04() throws ParseException {
        long curTime = System.currentTimeMillis();
        int i = 1004;
        String birth = "1995-10-23";
        String operator = "李白";
        String password = "123456";
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setUserId(Long.valueOf(i));
        sysUserDO.setName("郭靖");
        sysUserDO.setUsername("mai" + i);
        sysUserDO.setPassword(MD5Util.encrypt(password));
        sysUserDO.setGender(i % 2);
        sysUserDO.setPhone("18810165025");
        sysUserDO.setEmail("1985529368@qq.com");
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

    @Test
    public void test06(){
//        String avatar = "http://manage.javaweb.vip//images/admin/20200722/20200722133834275.jpg";
//        String remark = "这是一个备注";
        String udateOperator = "mai";
        SysUserQuery query = new SysUserQuery();
        List<SysUserDO> sysUserDOList = sysUserService.queryList(query);
        System.out.println(sysUserDOList.size());
        sysUserDOList.forEach(source -> {
            source.setUpdateOperator(udateOperator);
            sysUserService.insertOrUpdate(source);
        });

    }

    @Test
    public void test07(){
        SysUserQuery query = new SysUserQuery();
        query.setGender(1);
        query.setStatus(1);
        QueryWrapper<SysUserDO> queryWrapper = new QueryWrapper<>();
        // 姓名/员工ID/用户名/手机号
        if (!StringUtils.isEmpty(query.getKeywords())) {
            queryWrapper.like("name", query.getKeywords())
                    .or().like("username", query.getKeywords())
                    .or().like("phone", query.getKeywords())
                    .or().eq("user_id", query.getKeywords());
        }
        // 性别:0女 1男
        if (query.getGender() != null) {
            queryWrapper.eq("gender", query.getGender());
        }
        // 状态：0正常 1禁用
        if (query.getStatus() != null) {
            queryWrapper.eq("status", query.getStatus());
        }
        // 创建人/更新人
        if (query.getOperator() != null) {
            queryWrapper.like("operator", query.getOperator())
                    .or().like("update_operator", query.getOperator());
        }
        //queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("user_id");
//        queryWrapper.eq("gender", query.getGender());
//        queryWrapper.eq("status", query.getStatus());
        //查询数据
        IPage<SysUserDO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<SysUserDO> data = sysUserMapper.selectPage(page, queryWrapper);
        List<SysUserDO> sysUserDOList = data.getRecords();
        System.out.println(sysUserDOList.size());
        sysUserDOList.forEach(System.out :: println);
    }

    @Test
    public void test08(){
        SysUserQuery query = new SysUserQuery();
        query.setGender(1);
        query.setStatus(1);
        JsonResult result = sysUserService.getList(query);
        List<SysUserVO> list = (List<SysUserVO>) result.getData();
        System.out.println(list.size());
        list.forEach(System.out :: println);
    }
}
