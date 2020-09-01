package com.atlantico.rabbit;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.atlantico.data.vo.EmailMessage;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${javainuse.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(EmailMessage email) {
		rabbitTemplate.convertAndSend(exchange, routingkey, email);
		System.out.println("Send msg = " + email);
	    
	}
}