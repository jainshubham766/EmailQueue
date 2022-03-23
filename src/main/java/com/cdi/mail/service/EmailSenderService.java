package com.cdi.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cdi.mail.domain.MyEmailqueue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail( String to, String subject, String text) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(text);
		
		javaMailSender.send(msg);
		System.out.println("Mail Sent Successfully!");
	}


}
