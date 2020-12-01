package com.bupt.dql.web.enums;


import org.apache.commons.lang3.StringUtils;

public enum IndexEnum {
    ALL(-1, "全部"), C(0, "C"), CPLUSPLUS(1, "C++"), CSHARP(2, "C#"), JAVA(3, "Java"),
    JAVASCRIPT(4, "JavaScript"), PYTHON(5, "Python"), RUBY(6, "Ruby");

    private int code;

    private String name;

    private IndexEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (IndexEnum indexEnum : IndexEnum.values()) {
            if (indexEnum.code == code) {
                return indexEnum.getName();
            }
        }
        return StringUtils.EMPTY;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
