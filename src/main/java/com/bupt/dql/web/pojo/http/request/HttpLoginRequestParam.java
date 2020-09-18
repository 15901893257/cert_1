package com.bupt.dql.web.pojo.http.request;

import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/14
 */
@Data
public class HttpLoginRequestParam {
    private String username;

    private String password;

    private String verCode;
}
