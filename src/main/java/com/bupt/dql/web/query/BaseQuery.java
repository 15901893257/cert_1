package com.bupt.dql.web.query;

import lombok.Data;

/**
 * 查询对象基类
 *
 * @author: mai
 * @date: 2020/9/22
 */
@Data
public class BaseQuery {

    /**
     * 页码(默认1)
     */
    private Integer page = 1;

    /**
     * 每页数(默认：20)
     */
    private Integer limit = 20;
}
