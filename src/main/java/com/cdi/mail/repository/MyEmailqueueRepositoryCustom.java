package com.cdi.mail.repository;

import com.cdi.mail.domain.MyEmailqueue;

public interface MyEmailqueueRepositoryCustom {

	public MyEmailqueue addToMailQueue(MyEmailqueue myEmailqueue);

	public void sendPendingEmails();
}
