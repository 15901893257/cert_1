package com.bupt.dql.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * @author: mai
 * @date: 2019/9/24
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TimeFormatTest {

    @Test
    public void testTimeFormat(){
        Date date = new Date();
        System.err.println("${spring.jackson.date-format}");
        System.err.println(date);
    }
}
