package com.bupt.dql.web.enums;

import org.apache.commons.lang.StringUtils;

public enum SysUserTypeEnum {
    GENERAL(0, "普通用户"), ADMIN(1, "管理员");

    private int code;

    private String name;

    private SysUserTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public static String getName(int code){
        for (SysUserTypeEnum sysUserTypeEnum : SysUserTypeEnum.values()) {
            if (sysUserTypeEnum.code == code) {
                return sysUserTypeEnum.getName();
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
