package com.bupt.dql.common.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: mai
 * @date: 2019/7/3
 * 将es中的时间戳转换为正常日期格式
 */
public class DateUtil {

    private static final String DAY_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     *  long转换成日期格式
     */
    public static String longToDay(long second) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DAY_FORMAT);
        return simpleDateFormat.format(second);
    }

    /**
     *  long转换成日期格式
     */
    public static String longToTime(long second) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
        return simpleDateFormat.format(second);
    }

    /**
     *  String转换成日期格式
     */
    public static String stringToDay(String time) {
       return longToDay(Long.valueOf(time));
    }

    /**
     *  String转换成日期格式
     */
    public static String stringToTime(String time) {
        return longToTime(Long.valueOf(time));
    }

    /**
     *  String转换成日期格式
     */
    public static Date stringToDate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat;
        if (time.length() == 10) {
            simpleDateFormat = new SimpleDateFormat(DAY_FORMAT);
        } else {
            simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
        }
        Date date = simpleDateFormat.parse(time);
        return date;
    }
}
