package com.hcl.usf.util;


import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/***
 * This class is for reference for testing purpose
 * @author intakhabalam.s@hcl.com
 */
public class ManualMailSender {
	private static final Logger LOGGER = LogManager.getLogger(ManualMailSender.class);
	private static final String HOST = "smtp.usfood.com";
	private static final int PORT = 25;
	private static final String USER_NAME = "noreply@usfoods.com";
	private static final String PASSWORD = "";

	private final String from = "noreply@usfoods.com";
	private final String to = "ECOMOffshore@usfoods.com";

	private final String subject = "Test with testy sweet";
	private final String messageContent = "Test with testy sweet";

	/**
	 * 
	 * @return
	 */
	public Properties getEmailProperties() {
		final Properties config = new Properties();
		config.put("mail.smtp.auth", "true");
		config.put("mail.smtp.starttls.enable", "false");
		config.put("mail.smtp.host", HOST);
		config.put("mail.smtp.port", PORT);
		return config;
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void simpleEmailSend() throws IOException {
		final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}

		});

		try {
			final Message message = new MimeMessage(session);
			// message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setText(messageContent);
			message.setSentDate(new Date());
			/*String filename2 = "abc path";// change
																											// accordingly
			MimeBodyPart messageBodyPart3 = new MimeBodyPart();
			DataSource source2 = new FileDataSource(filename2);
			messageBodyPart3.setDataHandler(new DataHandler(source2));
			messageBodyPart3.setFileName(filename2);
			messageBodyPart3.attachFile(filename2);
			messageBodyPart3.attachFile(filename2);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart3);

			message.setContent(multipart);*/

			Transport.send(message);
		} catch (MessagingException ex) {
			LOGGER.error("Error sending mail {}  " + ex.getMessage(), ex);
		}
	}

	public static void main(String[] args) {
		try {
			new ManualMailSender().simpleEmailSend();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
