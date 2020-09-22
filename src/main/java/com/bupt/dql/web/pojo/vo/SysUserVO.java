package com.bupt.dql.web.pojo.vo;

import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Data
public class SysUserVO {
    /**
     *  主键id
     */
    private Long id;

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
    private String telephone;

    /**
     *  邮箱
     */
    private String email;

    /**
     *  出生日期
     */
    private String birthDay;

    /**
     *  最近登录ip
     */
    private String loginIp;

    /**
     *  最近登录时间
     */
    private String loginTime;

    /**
     *  创建时间
     */
    private String ctime;

    /**
     *  更新时间
     */
    private String utime;

    /**
     *  创建人
     */
    private String operator;

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
