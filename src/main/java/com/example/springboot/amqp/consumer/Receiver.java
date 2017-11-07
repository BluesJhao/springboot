package com.example.springboot.amqp.consumer;

import com.alibaba.fastjson.JSON;
import com.example.springboot.model.master.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * Desc ：Receiver Listener
 * Created by JHAO on 2017/11/6.
 */
public class Receiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println((User)JSON.parseObject(message.getBody(), User.class));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//手动确认消息,队列中持久化的消息会被删除
    }
}
