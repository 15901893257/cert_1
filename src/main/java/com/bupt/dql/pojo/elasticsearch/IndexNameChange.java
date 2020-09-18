package com.bupt.dql.pojo.elasticsearch;

import com.bupt.dql.pojo.param.ElasticParam;

/**
 * @author: mai
 * @date: 2019/9/5
 */

public class IndexNameChange {

    public static void changeIndexName(ElasticParam elasticParam){
        if(elasticParam.getLanguage().equals("java")){
            elasticParam.setLanguage("Java");
        }
        if(elasticParam.getLanguage().equals("c")){
            elasticParam.setLanguage("C");
        }
        if(elasticParam.getLanguage().equals("javascript")){
            elasticParam.setLanguage("JavaScript");
        }
        if(elasticParam.getLanguage().equals("ruby")){
            elasticParam.setLanguage("Ruby");
        }
    }
}
