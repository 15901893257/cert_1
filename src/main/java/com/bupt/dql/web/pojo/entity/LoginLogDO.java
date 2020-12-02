package com.bupt.dql.web.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("login_log")
public class LoginLogDO implements Serializable {

    private static final long serialVersionUID = -2443270452148501343L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String username;

    /**
     * 登陆类型 0-登陆系统 1-退出系统
     */
    private Integer loginType;

    /**
     * 0-成功 1-失败
     */
    private Integer result;

    private String loginIp;

    private Long loginTime;

    private String browser;
}
