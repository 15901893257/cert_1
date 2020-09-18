package com.bupt.dql.service.rabbitmq.producer;

import com.bupt.dql.pojo.rabbitmq.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * producer
 * @author: mai
 * @date: 2019/9/23
 */

@Service
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) throws Exception{
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
//        correlationData.setId(order.getId());
        rabbitTemplate.convertAndSend("order-exchange",
                "order.queue",
                order,
                correlationData);  //correlationData 消息唯一ID
    }

}
