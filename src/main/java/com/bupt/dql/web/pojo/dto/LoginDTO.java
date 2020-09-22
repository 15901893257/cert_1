package com.bupt.dql.web.pojo.dto;

import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/14
 */
@Data
public class LoginDTO {
    private String username;

    private String password;

    private String verCode;
}
