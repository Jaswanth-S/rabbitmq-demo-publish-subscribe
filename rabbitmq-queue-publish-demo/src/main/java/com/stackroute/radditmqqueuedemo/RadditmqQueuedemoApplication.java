package com.stackroute.radditmqqueuedemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RadditmqQueuedemoApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	public static void main(String[] args) {
		SpringApplication.run(RadditmqQueuedemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		rabbitTemplate.convertAndSend("message from demo app");
//		rabbitTemplate.convertAndSend("test_exchange","testRouting","hello ");

		SimpleMessage simpleMessage = new SimpleMessage();
		simpleMessage.setName("message1");
		simpleMessage.setDescription("message1 description");

		rabbitTemplate.convertAndSend("MyTopicExchange","topic",simpleMessage);
	}

}
