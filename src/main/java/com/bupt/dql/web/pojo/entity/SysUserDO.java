package com.bupt.dql.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bupt.dql.web.common.BaseEntity;
import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/14
 */
@Data
@TableName("sys_user")
public class SysUserDO extends BaseEntity {

    /**
     *  员工id
     */
    private Long userId;

    /**
     *  姓名
     */
    private String name;

    /**
     *  用户名
     */
    private String username;

    /**
     *  密码
     */
    private String password;

    /**
     *  性别
     *  0-女，1-男
     */
    private Integer gender;

    /**
     *  手机号
     */
    private String phone;

    /**
     *  邮箱
     */
    private String email;

    /**
     *  出生日期
     */
    private Long birthDay;

    /**
     *  最近登录ip
     */
    private String loginIp;

    /**
     *  最近登录时间
     */
    private Long loginTime;

    /**
     *  用户状态
     *  0-有效，1-无效
     */
    private Integer status;

    /**
     *  用户类别
     *  0-普通用户，1-管理员
     */
    private Integer uType;

    /**
     *  头像
     */
    private String avatar;

}
