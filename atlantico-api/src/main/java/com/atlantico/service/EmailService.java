package com.atlantico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantico.data.vo.EmailMessage;
import com.atlantico.rabbit.RabbitMQSender;

@Service
public class EmailService {


	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@Autowired
	UserService usuService;
	
	
	public void sendEmail(EmailMessage email) {
		if(email.isEmpty()) {
			email.setEmails(usuService.findEmailAllAdmin());
		}
		rabbitMQSender.send(email);
	}
}
