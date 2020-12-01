package com.bupt.dql.test;

import com.bupt.dql.common.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;

/**
 * @author: mai
 * @date: 2020/9/22
 */
public class DateUtilTest {

    @Test
    public void test01(){
        long now = System.currentTimeMillis();
        System.out.println(now);
        System.out.println(DateUtil.longToDay(now));
        System.out.println(DateUtil.longToTime(now));
    }

    @Test
    public void test02() throws ParseException {
        String birth = "1995-08-23";
        String day = "1995-08-23 22:23:00";
        System.out.println(DateUtil.stringToDate(birth).getTime());
        System.out.println(DateUtil.stringToDate(day).getTime());
        System.out.println(DateUtil.stringToDate(birth));
        System.out.println(DateUtil.stringToDate(day));
    }

    @Test
    public void testStringToLong() throws ParseException {
        String time = "2020-08-23";
        long a = DateUtil.stringToLong(time);
        System.out.println(a);
        String day = DateUtil.longToDay(a);
        System.out.println(day);
    }
}
