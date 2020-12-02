package com.bupt.dql.web.pojo.vo;

import lombok.Data;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
@Data
public class LoginLogVO {
    private Long id;

    private String username;

    private Long userId;

    /**
     * 登陆类型 0-登陆系统 1-退出系统
     */
    private Integer loginType;

    /**
     * 0-成功 1-失败
     */
    private Integer result;

    private String loginIp;

    private String loginTime;

    private String browser;
}
