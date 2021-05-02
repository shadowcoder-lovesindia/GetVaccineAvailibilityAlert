package com.vaccine.getavailibility.service;



	import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.core.io.ClassPathResource;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.mail.javamail.JavaMailSender;
	import org.springframework.mail.javamail.MimeMessageHelper;
	import org.springframework.stereotype.Service;
	import org.thymeleaf.context.Context;
	import org.thymeleaf.spring5.SpringTemplateEngine;

import com.vaccine.getavailibility.configuration.PropertiesCache;
import com.vaccine.getavailibility.model.MailMessageRequest;
import com.vaccine.getavailibility.model.MailMessageResponse;
import com.vaccine.getavailibility.model.Response;


	@Service
	public class MailServiceImpl  {


		
		public void sendmail(List<Response> responseList) throws AddressException, MessagingException, IOException {
			   Properties props = new Properties();
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.starttls.enable", "true");
			   props.put("mail.smtp.host", PropertiesCache.getInstance().getProperty("spring.mail.host"));
			   props.put("mail.smtp.port", PropertiesCache.getInstance().getProperty("spring.mail.port"));
			   
			   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			         return new PasswordAuthentication(PropertiesCache.getInstance().getProperty("spring.mail.username"), PropertiesCache.getInstance().getProperty("spring.mail.password"));
			      }
			   });
			   Message msg = new MimeMessage(session);
			   msg.setFrom(new InternetAddress( PropertiesCache.getInstance().getProperty("email.from"), false));
			   String toAddress[] = PropertiesCache.getInstance().getProperty("email.to").split(",");
			   String ccAddress[] = PropertiesCache.getInstance().getProperty("email.cc").split(",");
			   String bccAddress[] = PropertiesCache.getInstance().getProperty("email.bcc").split(",");
			   
			   for(String add:toAddress)
			   {
			   msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(add));
			   }
			   for(String add:ccAddress)
			   {
			   msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(add));
			   }
			   for(String add:bccAddress)
			   {
			   msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(add));
			   }
			   msg.setSubject("Appointment availibility Alert");
			   msg.setContent("Appointment available Alert", "text/html");
			   msg.setSentDate(new Date());

			   MimeBodyPart messageBodyPart = new MimeBodyPart();
			   String html="<body><table>";
			   for(Response response:responseList)
			   {
				   html=html+"<tr>"+"<td>"+response.getCentername()+"</td><td>"+"::Available capacity is ::"+"<b>"+response.getAvailablecapacity()+"</b></td><td>"+response.getSessiondate()+"</td><td>"+"::Minimum age limit is ::"+response.getSessionmin_age_limit()+"</td></tr>";
			   }
			   html=html+"</body></table>";
			   messageBodyPart.setContent(html, "text/html");

			   Multipart multipart = new MimeMultipart();
			   multipart.addBodyPart(messageBodyPart);
			
			   msg.setContent(multipart);
			   Transport.send(msg);   
			}


	}

