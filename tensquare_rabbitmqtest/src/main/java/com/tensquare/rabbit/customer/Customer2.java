package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test1")
public class Customer2 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("test1:" + msg);
    }

}
