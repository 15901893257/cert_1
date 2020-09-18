package com.bupt.dql.pojo.param;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: mai
 * @date: 2019/9/5
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
public class ElasticParam {

    private String keyword;
    private String language;
}
