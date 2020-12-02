package com.bupt.dql.test;

import java.util.Random;

import org.junit.Test;

/**
 * @author dengquanliang
 * Created on 2020/11/30
 */
public class LocalHostTest {

    @Test
    public void test() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            System.out.println(random.nextInt(2));
        }
    }
}
