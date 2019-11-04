package com.stackroute.rabbitmqlisternerdemo;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String MY_QUEUE = "MyQueue";

    //creating a queue
    @Bean
    Queue myQueue(){
        return new Queue(MY_QUEUE,true);
    }

    //create an exchange to bind to above queue
    @Bean
    Exchange myExchange(){
        return ExchangeBuilder.topicExchange("MyTopicExchange")
                .durable(true)
                .build();
    }


    @Bean
    Binding binding(){
        //bean way of creating binding for exchange and queue
//        return new Binding(MY_QUEUE,Binding.DestinationType.QUEUE,"MyTopicExchange","topic",null);

        //Declarative way using BindingBuilder
        return BindingBuilder
                .bind(myQueue())
                .to(myExchange())
                .with("topic")
                .noargs();
    }




    //creation connection using connection factory
    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    //add all the things(queue,connection,message listener class) in message listener container
    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.addQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return simpleMessageListenerContainer;
    }
}

