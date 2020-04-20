package com.expense.manage.ExpenseManagement.mail;

import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.expense.manage.ExpenseManagement.service.ExpenseMangService;

@Service("mailService")
public class MailService {

	@Autowired
	public ExpenseMangService expnseService;

	private JavaMailSender javaMailSender;

	private static final Logger LOG = LogManager.getLogger(MailService.class);

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String getBodyPart(String userId) {

		String htmlPart = "<html><body><table> <tr> <th>MOnth and Year</th><th>Expense</th></tr>";
		Map<String, String> summaryMap = expnseService.showSummaryExpense(userId);
		for (String key : summaryMap.keySet()) {
			htmlPart = htmlPart + "<tr><td>" + key + "</td><td>" + summaryMap.get(key) + "</td></tr>";
		}
		htmlPart = htmlPart + "</table></body></html>";
		return htmlPart;

	}

	public void sendEmail(String userId) throws Exception {

		try {

			MimeMessage message = javaMailSender.createMimeMessage();
			System.out.println(System.getProperty("java.ext.dirs"));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userId));
			message.setSubject("Expense Summary");

			MimeMultipart multipart = new MimeMultipart("alternative");

			// adding HTML content to email
			String content = getBodyPart(userId);
			MimeBodyPart htmlPart = new javax.mail.internet.MimeBodyPart();
			htmlPart.setContent(content, "text/html");
			multipart.addBodyPart(htmlPart);

			message.setContent(multipart);

			javaMailSender.send(message);
		}
		catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Error occured while sending email MessagingException " + userId);

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured while sending email " + userId);
		}
	}

}
