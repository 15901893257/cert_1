package com.bupt.dql.web.enums;


import org.apache.commons.lang3.StringUtils;

/**
 *  性别
 */
public enum GenderEnum {

    FEMALE(0, "女"), MALE(0, "男");

    private int code;

    private String name;

    private GenderEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if (genderEnum.code == code) {
                return genderEnum.getName();
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
