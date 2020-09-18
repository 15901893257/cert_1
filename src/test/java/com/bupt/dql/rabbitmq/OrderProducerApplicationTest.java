package com.bupt.dql.rabbitmq;

import com.bupt.dql.pojo.rabbitmq.Order;

import com.bupt.dql.service.rabbitmq.producer.OrderSender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

/**
 * Test Producer
 * @author: mai
 * @date: 2019/9/23
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:application.properties"})
public class OrderProducerApplicationTest {

    @Autowired
    private OrderSender orderSender;

    @Test
    public void testSend1() throws Exception{
        Order order = new Order();
        order.setId("20199230006");
        order.setName("第六个测试");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        System.out.println(order.getMessageId());
        orderSender.send(order);
    }

    @Test
    public void testSend2() throws Exception{
        for(int i = 1; i < 10; i++){
            Order order = new Order();
            order.setId(String.valueOf(i));
            order.setName("第"+i+"个测试");
            order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
            orderSender.send(order);
        }
    }

}
