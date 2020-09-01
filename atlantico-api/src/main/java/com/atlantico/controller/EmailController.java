package com.atlantico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantico.data.vo.EmailMessage;
import com.atlantico.rabbit.RabbitMQSender;
import com.atlantico.service.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Email EndPoint", tags = {"EmailEndPoint"})
@RestController
@RequestMapping("/v1.0/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@ApiOperation(value = "send email using rabbitmq")
	@PostMapping
	public String sendMessage(@Valid @RequestBody EmailMessage email) {
		emailService.sendEmail(email);
		return "Message sent to the RabbitMQ Successfully";
	}
}
