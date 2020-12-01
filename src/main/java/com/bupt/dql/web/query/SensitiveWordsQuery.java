package com.bupt.dql.web.query;

import com.bupt.dql.web.common.BaseQuery;

import lombok.Data;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
@Data
public class SensitiveWordsQuery extends BaseQuery {
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
     * 创建人或更新人
     */
    private String operator;

}
