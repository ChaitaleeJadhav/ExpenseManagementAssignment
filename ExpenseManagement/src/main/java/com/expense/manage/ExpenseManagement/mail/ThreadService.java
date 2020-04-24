package com.expense.manage.ExpenseManagement.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("threadService")
public class ThreadService {

	@Autowired
	private MailService mailService;

	private static final Logger LOG = LogManager.getLogger(ThreadService.class);

	public String callToMail(String userId) {
		ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
		emailExecutor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					mailService.sendEmail(userId);
				}
				catch (Exception e) {
					LOG.error("failed to send Mail", e);
				}
			}
		});

		emailExecutor.shutdown();
		return "Sending a Mail To User";
	}

}
