package com.example.springboot.amqp.consumer;

import com.alibaba.fastjson.JSON;
import com.example.springboot.model.master.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Desc ：Primary Consumer
 * Created by JHAO on 2017/11/4.
 */

@Component
public class PrimaryConsumer {

//    //框架提供监听器，配置auto-startup=true
//    @RabbitListener(queues = "test_queue")
//    public void receive(Message message, Channel channel) throws IOException {
//
//        System.out.println("received : " + message);
//        //手动确认
//        throw new AmqpRejectAndDontRequeueException("123");
//        System.out.println((User) JSON.parseObject(message.getBody(), User.class));
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//        //拦截AmqpRejectAndDontRequeueException异常，并保存其中的Message,消息将丢弃
//    }
}
