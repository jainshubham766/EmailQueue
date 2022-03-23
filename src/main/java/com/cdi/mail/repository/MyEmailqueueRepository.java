package com.cdi.mail.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cdi.mail.domain.MessageStatus;
import com.cdi.mail.domain.MyEmailqueue;

@Repository
public interface MyEmailqueueRepository
		extends MongoRepository<MyEmailqueue, Serializable>, MyEmailqueueRepositoryCustom {

	public List<MyEmailqueue> findByStatus(MessageStatus status);
}