package com.expense.manage.ExpenseManagement.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {

	private JavaMailSender javaMailSender;

	private static final Logger LOG = LogManager.getLogger(MailService.class);

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(String userId) throws Exception {

		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot.
		 * This JavaMailSender extends the MailSender Interface which contains
		 * send() function. SimpleMailMessage Object is required because send()
		 * function uses object of SimpleMailMessage as a Parameter
		 */
		try {
			MimeMessage message = javaMailSender.createMimeMessage();

			message.setFrom(new InternetAddress("chaitu16j@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("chaitalee16j@gmail.com"));
			message.setSubject("hi");
			message.setContent("HI", "text/html");

			MimeMultipart multipart = new MimeMultipart("alternative");

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("HI");
			multipart.addBodyPart(textPart);

			// adding HTML content to email
			MimeBodyPart htmlPart = new javax.mail.internet.MimeBodyPart();
			htmlPart.setContent("hi", "text/html");
			multipart.addBodyPart(htmlPart);

			message.setContent(multipart);

			javaMailSender.send(message);
		}
		catch (AddressException e) {
			// e.printStackTrace();
			System.out.println("Error occured while sending email AddressException" + userId);

		}
		catch (MessagingException e) {
			// e.printStackTrace();
			System.out.println("Error occured while sending email MessagingException " + userId);

		}
		catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error occured while sending email " + userId);
		}
	}

}
