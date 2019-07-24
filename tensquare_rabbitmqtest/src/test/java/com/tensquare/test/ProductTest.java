package com.tensquare.test;

import com.tensquare.rabbit.RabbitMQApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //直接模式
    @Test
    public void setMsg1(){
        rabbitTemplate.convertAndSend("test","直接模式测试");
    }

    //分裂模式
    @Test
    public void setMsg2(){
        rabbitTemplate.convertAndSend("testtest","","分裂模式测试");
    }

    //主题模式
    @Test
    public void setMsg3(){
        rabbitTemplate.convertAndSend("topictest","good.good.log","主题模式测试");
    }

}
