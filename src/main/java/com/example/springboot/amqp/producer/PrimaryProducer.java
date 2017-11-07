package com.example.springboot.amqp.producer;

import com.example.springboot.amqp.common.ConstantMQ;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * Desc ：Producer
 * Created by JHAO on 2017/11/3.
 */
@EnableRetry
@Component
public class PrimaryProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    public void initRabbitMQConfiguration() throws InterruptedException {
        //此段代码可在RabbitMqConfiguration中配置,只针对消息发送，消息接受只需配置监听器即可
        DirectExchange directEx = new DirectExchange(ConstantMQ.TEST_EXCHANGE_NAME);
        Queue queue = new Queue(ConstantMQ.TEST_QUEUE_NAME, true);//默认就是持久化
        amqpAdmin.declareExchange(directEx);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(directEx).with(ConstantMQ.ROUTING_KEY));
        ((RabbitTemplate)amqpTemplate).setConfirmCallback(this);//设置生产者与队列的回调
        ((RabbitTemplate)amqpTemplate).setReturnCallback(this);
    }

    public void send(String routKey, Object object){

        amqpTemplate.convertAndSend(ConstantMQ.TEST_EXCHANGE_NAME,routKey, object);
        //correlationData是回调时传入回调方法的参数，因此通过这个属性来区分消息，并进行重发。
//        CorrelationData correlationData = new CorrelationData("123");
//        ((RabbitTemplate)amqpTemplate).convertAndSend(routKey, object, correlationData);
//        amqpTemplate.convertAndSend(routKey, object);
    }

    /**
     * 功能: 确认消息是否到达Exchange
     * ConfirmCallback接口的confirm方法用于实现消息发送到RabbitMQ交换器后接收ack回调
     * 当Exchange找不到时ack为false
     * @param correlationData 与Producer.send()相关联标识
     * @param ack 是否确认
     * @param cause 异常信息
     *
     * */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("关联send回调：" + correlationData);
        if (ack) {
            System.out.println("消息发送确认成功");
        } else {
            System.out.println("消息发送到达Exchange确认失败:" + cause);
        }
    }

    /**
     * 功能：当找到Exchange，但是没有匹配的Routing时，会将消息返回
     * 然后还会调用confirm(),返回的ack为true
     * 前提需要设置了 Mandatory=true，不设置消息会直接丢弃
     *
     * */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息发送找不到Routing Key :" + message);
        //保存消息，进行后续处理
    }
}
