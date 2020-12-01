package com.bupt.dql.web.pojo.vo;

import lombok.Data;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
@Data
public class SensitiveWordsVO {
    /**
     * id
     */
    private Long id;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 0-关键字 1-正则表达式
     */
    private Integer keyType;

    /**
     * 语言
     *  -1-all 0-c 1-c++ 2-c# 3-java 4-javascript 5-python 6-ruby
     */
    private Integer indexType;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人或更新人
     */
    private String operator;

    /**
     * 创建时间
     */
    private String ctime;

    /**
     * 更新时间
     */
    private String utime;

    /**
     * 状态,0-有效 1-无效
     */
    private Integer status;
}
