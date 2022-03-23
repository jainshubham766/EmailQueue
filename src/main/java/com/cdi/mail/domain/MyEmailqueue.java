package com.cdi.mail.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "guest")
public class MyEmailqueue {

	@Id
	private String id;

	private String to;

	private String subject;

	private String text;

	@CreatedDate
	private LocalDateTime createdAt;

	private MessageStatus status;
}
