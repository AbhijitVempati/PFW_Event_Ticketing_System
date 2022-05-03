package com.springdemo.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

	public static void sendMail(String recepient) throws Exception {
		
		System.out.println("Preparing to send mail");
		
		Properties properties = new Properties();
		
		// mail.smtp.auth
		properties.put("mail.smtp.auth", true);
		
		//
		properties.put("mail.smtp.starttls.enable", true);
		
		//
		
		properties.put("mail.smtp.host", "smtp.gmail.com");
		//
		properties.put("mail.smtp.port", "587");
		
		String myAccount = "abhijitv16@gmail.com";
		String password = "rogeR@123$";
		
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccount, password);
			}
		});
		
		Message message = prepareMessage(session, myAccount, recepient);
		
		Transport.send(message);
		System.out.println("Mail Sent");
	}
	
	private static Message prepareMessage(Session session, String myAccount, String recepient) {
		
	
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Test Mail");
			message.setText("Testing mail service");
			return message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
