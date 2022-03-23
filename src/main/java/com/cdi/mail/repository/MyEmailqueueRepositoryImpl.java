package com.cdi.mail.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdi.mail.domain.MessageStatus;
import com.cdi.mail.domain.MyEmailqueue;
import com.cdi.mail.service.EmailSenderService;

public class MyEmailqueueRepositoryImpl implements MyEmailqueueRepositoryCustom {

	@Autowired
	private MyEmailqueueRepository myEmailqueueRepository;

	private static final Logger LOG = LoggerFactory.getLogger(MyEmailqueueRepositoryImpl.class);

	@Override
	public com.cdi.mail.domain.MyEmailqueue addToMailQueue(MyEmailqueue myEmailqueue){

		// Validation
		// validate if these fields are there: to, subject, text
		
		//Regular Expression   
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";  
		if(!myEmailqueue.getTo().matches(regex)) {
			System.out.println("Please Enter Valid Mail ID.");
			return myEmailqueue;
		}
		if (myEmailqueue.getSubject().length()==0	|| myEmailqueue.getText().length()==0) {
			System.out.println("Subject/ Mail body cannot be empty.");
			return myEmailqueue;
		}

		myEmailqueue = myEmailqueue.toBuilder().status(MessageStatus.PENDING).createdAt(LocalDateTime.now()).build();

		return myEmailqueueRepository.save(myEmailqueue);
	}

	@Override
	public void sendPendingEmails() {

		// fetch all pending emails
		List<MyEmailqueue> listOfPendinfEmails = myEmailqueueRepository.findByStatus(MessageStatus.PENDING);
		// list of email
		try {
		// iterate the list
		listOfPendinfEmails.forEach(email -> {

			// send email by calling sendEmail function
			EmailSenderService emailSenderService = new EmailSenderService();
			emailSenderService.sendEmail(email.getTo(), email.getSubject(), email.getText());

			// changing status to SENT
			email = email.toBuilder().status(MessageStatus.SENT).build();
			myEmailqueueRepository.save(email); 

		});
		
		} catch (Exception e) {
			System.out.println("Invaild I/P provied, Please enter valid data!");
		}

	}

}
