package com.bupt.dql.test;

import com.bupt.dql.constant.elasticsearch.RegexConstant;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模式匹配
 *
 * @author: mai
 * @date: 2019/9/18
 */
public class TestRegexMatchers {

    public boolean isValidIP(String regex, String ip) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    @Test
    public void test02() {
        String regex = RegexConstant.LOCAL_IP_REGEX;
        String ip1 = "255.255.255.255";
        String ip2 = "0.0.0.0";
        String ip3 = "192.168.0.32";
        String ip4 = "10.3.168.1";
        String ip5 = "10.103.243.25";
        String ip6 = "192.168.1.909";
        System.out.println(isValidIP(regex, ip1));
        System.out.println(isValidIP(regex, ip2));
        System.out.println(isValidIP(regex, ip3));
        System.out.println(isValidIP(regex, ip4));
        System.out.println(isValidIP(regex, ip5));
        System.out.println(isValidIP(regex, ip6));
    }


    @Test
    public void test03() {
        String regex = RegexConstant.LOCAL_IP_REGEX;
        String ip1 = "255.255.255.255";
        String ip2 = "0.0.0.0";
        String ip3 = "127.0.0.1";
        String ip4 = "10.0.255.136";
        String ip5 = "172.16.254.25";
        String ip6 = "172.31.254.36";
        String ip7 = "192.168.0.162";
        String ip8 = "165.0.0.127";
        String ip9 = "10.265.0.1";
        System.out.println("1: " + isValidIP(regex, ip1));
        System.out.println("2: " + isValidIP(regex, ip2));
        System.out.println("3: " + isValidIP(regex, ip3));
        System.out.println("4: " + isValidIP(regex, ip4));
        System.out.println("5: " + isValidIP(regex, ip5));
        System.out.println("6: " + isValidIP(regex, ip6));
        System.out.println("7: " + isValidIP(regex, ip7));
        System.out.println("8: " + isValidIP(regex, ip8));
        System.out.println("9: " + isValidIP(regex, ip9));
    }

    @Test
    public void test04(){
        String regex = "(172\\.((1[6-9])|(3[0-1]))(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){2})";
        String ip5 = "172.16.254.25";
        String ip6 = "172.31.254.36";
        String ip7 = "192.168.0.15";
        String ip8 = "172.31.256.36";
        String regex1 = "(192\\.168(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){2})";
        String ip1 = "192.168.0.255";
        String ip2 = "192.168.14.52";
        String ip3 = "168.192.25.11";
        System.out.println(isValidIP(regex1, ip1));
        System.out.println(isValidIP(regex1, ip2));
        System.out.println(isValidIP(regex1, ip3));
        System.out.println(isValidIP(regex, ip5));
        System.out.println(isValidIP(regex, ip6));
        System.out.println(isValidIP(regex, ip7));
        System.out.println(isValidIP(regex, ip8));

    }

    @Test
    public void test05(){
        String regex = RegexConstant.PUBLIC_IP_REGEX;
        String ip1 = "255.255.255.255";
        String ip2 = "0.0.0.0";
        String ip3 = "127.0.0.1";
        String ip4 = "10.0.255.136";
        String ip5 = "172.16.254.25";
        String ip6 = "172.31.254.36";
        String ip7 = "192.168.0.162";
        String ip8 = "165.0.0.127";
        String ip9 = "10.265.0.1";
        String ip10 = "240.0.0.3";
        System.out.println("1: " + isValidIP(regex, ip1));
        System.out.println("2: " + isValidIP(regex, ip2));
        System.out.println("3: " + isValidIP(regex, ip3));
        System.out.println("4: " + isValidIP(regex, ip4));
        System.out.println("5: " + isValidIP(regex, ip5));
        System.out.println("6: " + isValidIP(regex, ip6));
        System.out.println("7: " + isValidIP(regex, ip7));
        System.out.println("8: " + isValidIP(regex, ip8));
        System.out.println("9: " + isValidIP(regex, ip9));
        System.out.println("9: " + isValidIP(regex, ip10));
    }

    //判断是否存在重复的字符
    @Test
    public void test2() {
        String p1 = "^.*(\\w)\\1{2,}.*$";
        String p2 = "(\\w)\\1{2,}";    //匹配3次以上，\\1表示重复的那个字符
        Pattern pattern = Pattern.compile(p2);
        Matcher matcher = pattern.matcher("23111aabcccweqwe222");
//        System.out.println(matcher.matches());
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }
}
