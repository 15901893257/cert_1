package com.bupt.dql.common.util;

import java.text.SimpleDateFormat;

/**
 * @author: mai
 * @date: 2019/7/3
 * 将es中的时间戳转换为正常日期格式
 */
public class DateUtil {

    private static String format = "yyyy-MM-dd";

    //将时间搓转换为日期格式
    public String getFormat(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Long second = Long.parseLong(time);
        return simpleDateFormat.format(second);
    }

}
