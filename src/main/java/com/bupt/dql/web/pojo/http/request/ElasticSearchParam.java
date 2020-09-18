package com.bupt.dql.web.pojo.http.request;

import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/16
 */
@Data
public class ElasticSearchParam {
    //搜索类型：0-模糊匹配,1-正则
    private Integer type;

    //索引：0-c,1-cplusplus,2-csharp,3-java,4-javascript,5-python,6-ruby
    private Integer index;

    //关键字或正则表达式
    private String keyWord;
}
