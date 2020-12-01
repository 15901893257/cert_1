package com.bupt.dql.web.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
public enum StatusEnum {
    VALID(0, "有效"), INVALID(1, "无效");

    private int code;

    private String name;

    private StatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.code == code) {
                return statusEnum.getName();
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
