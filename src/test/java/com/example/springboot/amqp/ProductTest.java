package com.example.springboot.amqp;

import com.alibaba.fastjson.JSON;
import com.example.springboot.amqp.common.ConstantMQ;
import com.example.springboot.amqp.producer.PrimaryProducer;
import com.example.springboot.model.master.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Desc ：
 * Created by JHAO on 2017/11/4.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTest {

    @Autowired
    private PrimaryProducer primaryProducer;

    @Test
    public void sendTest(){
        User user = new User();
        user.setId(123);
        user.setUserName("foo1");
        user.setPassword("*********");
        primaryProducer.send("topic.1231231",user);
    }
}
