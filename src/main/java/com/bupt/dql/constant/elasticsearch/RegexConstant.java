package com.bupt.dql.constant.elasticsearch;

/**
 * @author: mai
 * @date: 2020/9/7
 */
public class RegexConstant {
    //所有ip
    public static final String IP_REGEX = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

    //内网ip
    public static final String LOCAL_IP_REGEX = "(127\\.0\\.0\\.1)|(10(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
            "(172\\.((1[6-9])|(3[0-1]))(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){2})|" +
            "(192\\.168(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){2})";

    //A类、B类、C类IP
    public static final String PUBLIC_IP_REGEX = "([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";


}
