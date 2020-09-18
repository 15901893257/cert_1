package com.bupt.dql.pojo.rabbitmq;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 序列化
 * @author: mai
 * @date: 2019/9/23
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
public class Order implements Serializable {

    private static final long serialVersionUID = 5455164495996077066L;

    private String id;

    private String name;

    private String messageId;  //存储消息的唯一标识
}
