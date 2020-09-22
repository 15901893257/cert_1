package com.bupt.dql.pojo;

import lombok.Data;

/**
 * 封装公共的Page对象，并让user对象继承page对象
 */
@Data
public class Page {
    //每页显示数量
    private int limit;
    //页码
    private int page;
    //sql语句起始索引
    private int offset;

}
