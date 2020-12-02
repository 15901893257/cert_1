package com.bupt.dql.web.query;

import com.bupt.dql.web.common.BaseQuery;

import lombok.Data;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
@Data
public class LoginLogQuery extends BaseQuery {
    private String username;
    private Integer loginType;
    private Integer result;
}
