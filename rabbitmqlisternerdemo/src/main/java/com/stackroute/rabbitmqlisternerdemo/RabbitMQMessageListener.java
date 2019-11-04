package com.stackroute.rabbitmqlisternerdemo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

//this is the message listener class
public class RabbitMQMessageListener implements MessageListener {

    //method to get message from queue
    @Override
    public void onMessage(Message message) {
        System.out.println("message = " + new String(message.getBody()));
    }
}
