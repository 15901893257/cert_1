package com.bupt.dql.elasticsearch.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: mai
 * @date: 2019/9/18
 */
public class testString {

    private int n = 0;
    private String a = "";

    public Boolean isEqual(String a,String b){
        boolean flag = false;
        if(a == b){
            flag = true;
        }
        return flag;
    }

    public void f(){
        n++;
        if(n > 10){
            return;
        }
        System.out.println(n);
    }

    @Test
    public void assertEqual(){
        f();
        Assert.assertEquals("错误",1,1);
        Assert.assertNotNull(a);
    }

    @Test
    public void test(){
        int n = 100;
        while (n > 0){
            f();
            n--;
        }
    }

    @Test
    public void testString(){
        String a = "DnF1ZXJ5VGhlbkZldGNoBAAAAAAACwzTFlVxb21ZQ0F3VGcyU0I1aFhFQW14emc" +
                "AAAAAAAHZJhZiNjhRLVFMNlNxT3BOekRUR0pKd19RAAAAAAAB2ScWYjY4US1RTDZTc" +
                "U9wTnpEVEdKSndfUQAAAAAAEPrMFlVCODNzT2NfUXBhdDRvcGJLNmxtNkE=";
        String b = "DnF1ZXJ5VGhlbkZldGNoBAAAAAAACwzTFlVxb21ZQ0F3VGcyU0I1aFhFQW14e" +
                "mcAAAAAAAHZJhZiNjhRLVFMNlNxT3BOekRUR0pKd19RAAAAAAAB2ScWYjY4US1RTDZ" +
                "TcU9wTnpEVEdKSndfUQAAAAAAEPrMFlVCODNzT2NfUXBhdDRvcGJLNmxtNkE=";
        System.out.println(isEqual(a,b));
    }

}
