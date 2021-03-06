package com.bupt.dql.web.query;

import com.bupt.dql.web.common.BaseQuery;

import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Data
public class SysUserQuery extends BaseQuery {

    /**
     * 姓名/员工ID/用户名/手机号
     */
    private String keywords;

    /**
     * 性别:1男 2女 3保密
     */
    private Integer gender;

    /**
     * 状态：0-正常 1-禁用
     */
    private Integer status;

    /**
     * 创建人
     */
    private String operator;

    /**
     * 更新人
     */
    private String updateOperator;
}
