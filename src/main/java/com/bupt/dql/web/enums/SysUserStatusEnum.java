package com.bupt.dql.web.enums;

import org.apache.commons.lang.StringUtils;

public enum SysUserStatusEnum {
    VALID(0, "启用"), INVALID(1, "禁用");

    private int code;

    private String name;

    private SysUserStatusEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public static String getName(int code){
        for (SysUserStatusEnum sysUserStatusEnum : SysUserStatusEnum.values()) {
            if (sysUserStatusEnum.code == code) {
                return sysUserStatusEnum.getName();
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
