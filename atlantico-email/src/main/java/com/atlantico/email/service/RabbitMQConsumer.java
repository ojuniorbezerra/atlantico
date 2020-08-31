package com.atlantico.email.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.atlantico.data.vo.EmailMessage;


@Component
public class RabbitMQConsumer {

	@RabbitListener(queues = "${javainuse.rabbitmq.queue}")
	public void recievedMessage(EmailMessage email) {
		System.out.println("Recieved Message From RabbitMQ: " + email);
	}
	

}