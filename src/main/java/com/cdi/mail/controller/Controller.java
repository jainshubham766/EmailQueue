package com.cdi.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdi.mail.domain.MyEmailqueue;
import com.cdi.mail.repository.MyEmailqueueRepository;

@org.springframework.stereotype.Controller
@RequestMapping(path = "/addToQueue")
public class Controller {

	@Autowired
	private MyEmailqueueRepository myEmailqueueRepository;


	@RequestMapping(path = "/post", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MyEmailqueue> post(@RequestBody MyEmailqueue email) {
//		System.out.println(guest);
		myEmailqueueRepository.addToMailQueue(email);
		return new ResponseEntity<MyEmailqueue>(email, HttpStatus.ACCEPTED);

	}
}