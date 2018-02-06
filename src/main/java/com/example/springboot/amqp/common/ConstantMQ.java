package com.example.springboot.amqp.common;

/**
 * Desc ：MQ Constant
 * Created by JHAO on 2017/11/6.
 */

public interface ConstantMQ {

    String TEST_QUEUE_NAME = "test_queue";
    String TEST_EXCHANGE_NAME = "test_exchange";
    String TOPIC_EXCHANGE_NAME = "topic_exchange";
    String ROUTING_KEY = "test";
    String TOPIC_ROUTING_KEY = "topic.*";
}
