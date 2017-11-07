package com.example.springboot.amqp.config;

import com.example.springboot.amqp.common.ConstantMQ;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;


/**
 * Desc ：RabbitMq Configuration
 * Created by JHAO on 2017/11/4.
 */

@Configuration
public class RabbitMqConfiguration {

    @Autowired
    private ConnectionFactory connectionFactory;

    // 1> 建立连接工厂。
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory =
//                new CachingConnectionFactory(mqRabbitHost, mqRabbitPort);
//        connectionFactory.setUsername(mqRabbitUsername);
//        connectionFactory.setPassword(mqRabbitPassword);
//        connectionFactory.setVirtualHost(mqRabbitVirtualHost);
//        return connectionFactory;
//    }

    /**
     * 2> 自定义RabbitMQ的消息发送模板，默认Template还需setXXX
     *
     */
//    @Bean("rabbitTemplate")
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        //此处也可以无需配置，在amqpTemplate.convertAndSend()指定Exchange和rouringKey
//        //rabbitTemplate.setExchange(ConstantMQ.TEST_EXCHANGE_NAME);
//        //rabbitTemplate.setRoutingKey(ConstantMQ.ROUTING_KEY);
//        rabbitTemplate.setMessageConverter(messageConverter());
//        //强制将信息发送到队列，结合publisher-confirms,publisher-returns使用
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setRetryTemplate(retryTemplate());
//        return rabbitTemplate;
//    }

    /**
     * 3>模式：AcknowledgeMode.AUTO, 用于消费者与队列的确认
     * 1,当抛出AmqpRejectAndDontRequeueException异常的时候，则消息会被拒绝，且requeue=false（不重新入队列）
     * 2,当抛出ImmediateAcknowledgeAmqpException异常，则消费者会被确认
     * 3,其他的异常，则消息会被拒绝，且requeue=true（如果此时只有一个消费者监听该队列，则有发生死循环的风险，多消费端也会造成资源的极大浪费，这个在开发过程中一定要避免的）。可以通过setDefaultRequeueRejected（默认是true）去设置
     *
     */
//    @Bean("listenerContainerFactory")
//    public SimpleRabbitListenerContainerFactory listenerContainerFactory(
//            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
//        SimpleRabbitListenerContainerFactory factory =
//                new SimpleRabbitListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//        //默认是AcknowledgeMode.AUTO
////        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        factory.setMessageConverter(messageConverter());
//        return factory;
//    }
    /**
     * 启动生产者重试机制RetryTemplate
     *
     * */
//    @Bean
//    public RetryTemplate retryTemplate() {
//        RabbitProperties.Retry retry = new RabbitProperties.Retry();
//        retry.setEnabled(true);
//        System.out.println(retry.getMaxAttempts());
//        RetryTemplate template = new RetryTemplate();
//        SimpleRetryPolicy policy = new SimpleRetryPolicy();
//        policy.setMaxAttempts(retry.getMaxAttempts());
//        template.setRetryPolicy(policy);
//        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
//        backOffPolicy.setInitialInterval(retry.getInitialInterval());
//        backOffPolicy.setMultiplier(retry.getMultiplier());
//        backOffPolicy.setMaxInterval(retry.getMaxInterval());
//        template.setBackOffPolicy(backOffPolicy);
//        return template;
//    }

    /**
     * RabbitMQ默认SimpleMessageConverter只支持传输Byte[]
     * SimpleMessageConverter支持对象传输
     *
     */
//    @Bean
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();//new SimpleMessageConverter();
//    }

    // 声明监听器
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, Receiver listenerAdapter) {
//
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(new String[]{ConstantMQ.TEST_QUEUE_NAME});
//        container.setMessageListener(listenerAdapter);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        container.setMessageConverter(messageConverter());
//        //listenerAdapter 实现ChannelAwareMessageListener接口进行手动确认
//        return container;
//    }



    // 在spring容器中添加消费者监听
//    @Bean
//    Receiver receiver() {
//        return new Receiver();
//    }

    //    @Bean
//    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }

    // 定义直连交换机
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange(ConstantMQ.TEST_EXCHANGE_NAME);
//    }
//    // 要求队列和直连交换机绑定，指定ROUTING_KEY
//    @Bean
//    Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(ConstantMQ.ROUTING_KEY);
//    }

//    @Autowired
//    private AmqpAdmin amqpAdmin;
//
//
//    @Bean
//    public Queue queue(){
//        return new Queue(ConstantMQ.TEST_QUEUE_NAME);
//    }
}
