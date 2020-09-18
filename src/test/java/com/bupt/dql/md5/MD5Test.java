package com.bupt.dql.md5;

import com.bupt.dql.common.util.MD5Util;
import org.junit.Test;

/**
 * @author: mai
 * @date: 2020/9/11
 */
public class MD5Test {

    @Test
    public void test(){
        String password = "123456";
        String pwdMd5 = MD5Util.encrypt(password);
        String pwdMD5 = MD5Util.encrypt(password);
        System.out.println(pwdMd5);
        System.out.println(pwdMD5.equals(pwdMd5));
    }
}
