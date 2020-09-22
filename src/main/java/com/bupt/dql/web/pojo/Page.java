package com.bupt.dql.web.pojo;

import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Data
public class Page {
    /**
     * 页码(默认1)
     */
    private Integer page = 1;

    /**
     * 每页数(默认：20)
     */
    private Integer limit = 20;
}
