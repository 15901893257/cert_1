package com.bupt.dql.web.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
public enum LoginStatusEnum {
    SUCCESS(0, "登陆系统"), FAIL(1, "退出系统");

    private int code;

    private String name;

    private LoginStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (LoginStatusEnum loginStatusEnum : LoginStatusEnum.values()) {
            if (loginStatusEnum.code == code) {
                return loginStatusEnum.getName();
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
