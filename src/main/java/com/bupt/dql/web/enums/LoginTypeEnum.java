package com.bupt.dql.web.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
public enum LoginTypeEnum {
    LOGIN(0, "登陆系统"), LOGOUT(1, "退出系统");

    private int code;

    private String name;

    private LoginTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (LoginTypeEnum loginTypeEnum : LoginTypeEnum.values()) {
            if (loginTypeEnum.code == code) {
                return loginTypeEnum.getName();
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
