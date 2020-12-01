package com.bupt.dql.web.enums;


import org.apache.commons.lang3.StringUtils;

/**
 *  es搜索类型
 */
public enum ElasticSearchEnum {
    MATCH_ALL(0, "模糊匹配"), REGEX(1, "正则");
    private int code;

    private String name;

    private ElasticSearchEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (ElasticSearchEnum searchEnum : ElasticSearchEnum.values()) {
            if (searchEnum.code == code) {
                return searchEnum.getName();
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
