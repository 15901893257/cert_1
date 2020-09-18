package com.bupt.dql.web.pojo;

import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/14
 */
@Data
public class SysUserDO {
    private Integer id;

    private String username;

    private String telephone;

    private String mail;

    private String password;

    //用户权限：0-普通用户，1-管理员
    private Integer status;

    //状态：0-有效，1-无效
    private Integer uType;
}
