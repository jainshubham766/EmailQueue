package com.cdi.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.cdi.mail.repository.MyEmailqueueRepository;
import com.cdi.mail.service.EmailSenderService;

@SpringBootApplication
@EnableScheduling
public class MailApplication {

	private final int ONE_MINUTE = 1000 * 60 * 1;

	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private MyEmailqueueRepository myEmailqueueRepository;

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);

	}

	@Scheduled(fixedDelay = ONE_MINUTE)
	public void scheduleFixedDelayTask() {
		System.out.println("Scheduler in Process!");
		new MailApplication().sendMail();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		myEmailqueueRepository.sendPendingEmails();
	}

}