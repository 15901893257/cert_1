package com.bupt.dql.web.enums;

import org.apache.commons.lang.StringUtils;

/**
 *  es索引类型
 */
public enum ElasticIndexEnum {
    C(0, "c"), CPLUSPLUS(1, "cplusplus"), CSHARP(2, "csharp"), JAVA(3, "java"),
    JAVASCRIPT(4, "javascript"), PYTHON(5, "python"), RUBY(6, "ruby");

    private int code;

    private String name;

    private ElasticIndexEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public static String getName(int code){
        for (ElasticIndexEnum indexEnum : ElasticIndexEnum.values()) {
            if (indexEnum.code == code) {
                return indexEnum.getName();
            }
        }
        return StringUtils.EMPTY;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
