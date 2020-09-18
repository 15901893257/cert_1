package com.bupt.dql.web.controller.rabbitmq;

import com.bupt.dql.pojo.rabbitmq.Order;
import com.bupt.dql.service.rabbitmq.producer.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author: mai
 * @date: 2019/9/23
 */
@Controller
@RequestMapping("rabbitmq")
public class OrderProducerController {

    @Autowired
    private OrderSender orderSender;

    @RequestMapping("test1")
    @ResponseBody
    public String testOrderProducer1() throws Exception {
        Order order = new Order();
        order.setId("20199230003");
        order.setName("第三个测试");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderSender.send(order);
        return order.getName();
    }

    @RequestMapping("test2")
    @ResponseBody
    public String testOrderProducer2() throws Exception{
        for(int i = 1; i < 10; i++){
            Order order = new Order();
            order.setId(String.valueOf(i));
            order.setName("第"+i+"个测试");
            order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
            orderSender.send(order);

        }
        return "success";
    }
}
