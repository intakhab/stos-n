package com.hcl.usf.service;

import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hcl.usf.common.LoadTemplate;
import com.hcl.usf.common.HTMLTemplate;
import com.hcl.usf.dto.CommonDto;
import com.hcl.usf.dto.MailDto;
import com.hcl.usf.util.AppUtil;
/***
 * @author intakhabalam.s@hcl.com
 * Common Mail Service use this class for mail sending  
 */
@Service
public class EmailService {

	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
	public final String GREET_MSG = "STOS Buddy";
	private CommonDto commonDto;
	@Autowired
	private Environment env;
	private static JavaMailSenderImpl mailSender;
	
	public JavaMailSender getJavaMailSender() {
		if (mailSender != null) {
			return mailSender;
		}
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost(commonDto.getHost());
		mailSender.setPort(Integer.valueOf(commonDto.getPort()));
		mailSender.setUsername(commonDto.getMailUserName());
		mailSender.setPassword(commonDto.getMailPassword());
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.debug", commonDto.getDebugMail());

		return mailSender;
	}
	
    /**
     * @param eParams {@link MailDto}
     */
	public void send(MailDto eParams) {

		if (eParams.isHtml()) {
			try {
				sendHtmlMail(eParams);
			} catch (MessagingException e) {
				logger.error("Could not send email to : {} Error1 = {}", eParams.getToAsList(), e.getMessage());
			} catch (UnsupportedEncodingException e) {
				logger.error("Could not send email to : {} Error2 = {}", eParams.getToAsList(), e.getMessage());

			}
		} else {
			sendPlainTextMail(eParams);
		}

	}
    /****
     */
	private void sendHtmlMail(MailDto eParams) throws MessagingException, UnsupportedEncodingException {
		boolean isHtml = true;
		MimeMessage message = getJavaMailSender().createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
		helper.setReplyTo(eParams.getFrom());
		helper.setPriority(1);
		helper.setFrom(eParams.getFrom(),GREET_MSG);
		helper.setSubject(eParams.getSubject());

		helper.setText(eParams.getMessage(), isHtml);
		
		if(!eParams.getInlineFilePath().isEmpty()) {
           helper.addInline("screenShotImage", Paths.get(eParams.getInlineFilePath()).toFile());
		}

		if (eParams.getCc().size() > 0) {
			helper.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
		}
		if (eParams.getFileURL() != null && eParams.getFileURL().exists()) {
			helper.addAttachment(eParams.getFileURL().getName(), eParams.getFileURL());
		}
		getJavaMailSender().send(message);
	}

	
	/***
	 * 
	 * @param eParams
	 */
	private void sendPlainTextMail(MailDto eParams) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		eParams.getTo().toArray(new String[eParams.getTo().size()]);
		mailMessage.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
		mailMessage.setReplyTo(eParams.getFrom());
		mailMessage.setFrom(eParams.getFrom());
		mailMessage.setSubject(eParams.getSubject());
		mailMessage.setText(eParams.getMessage());

		if (eParams.getCc().size() > 0) {
			mailMessage.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
		}

		getJavaMailSender().send(mailMessage);

	}
	

	public void sendEmailTemplate(MailDto mailDto) throws Exception {
		LoadTemplate template = new LoadTemplate(env.getProperty("email.template"));
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("body", mailDto.getMessage());
		String tdy=String.valueOf(new Date())+"  <==>  ( "+AppUtil.getIPHostName() +" )";
		replacements.put("today", tdy);
		String message = template.getTemplate(replacements);
		//MailDto mailDto = new MailDto(from, to, subject, message,fileURL);
		mailDto.setMessage(message);
		mailDto.setHtml(true);
		send(mailDto);
		logger.info("Mail Sent Succesfully...");
	}
	
	 /***
	  * 
	  * @param rdto
	  * @param mailDto 
	  */
	
	public void prepareMail(CommonDto cdto,MailDto mailDto) {
		this.commonDto=cdto;
		String string=HTMLTemplate.prepareMailBody(cdto);
		mailDto.setMessage(string);
		mailDto.setSubject(env.getProperty("ecom.mail.header"));
		try {
			sendEmailTemplate(mailDto);
		} catch (Exception e) {
			logger.error("Mail Sending problem {} "+e.getMessage());
		}
		
	}
	public void prepareRegMail(CommonDto cdto,MailDto mailDto,String templateBody) {
		this.commonDto=cdto;
		mailDto.setMessage(templateBody);
		mailDto.setSubject(env.getProperty("ecom.regression.health.header"));
		try {
			sendEmailTemplate(mailDto);
		} catch (Exception e) {
			logger.error("Mail Regression Sending problem {} "+e.getMessage());
		}
		
	}
}
