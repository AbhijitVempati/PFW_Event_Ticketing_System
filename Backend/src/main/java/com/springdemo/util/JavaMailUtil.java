package com.springdemo.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.springdemo.entity.Registration;

public class JavaMailUtil {

	public static void sendMail(Registration theRegistration) throws Exception {
		
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
		
		String myAccount = "*******"; //Write the senders email
		String password = "********";//Write the sender password
		
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccount, password);
			}
		});
		
		Message message = prepareMessage(session, myAccount, theRegistration);
		
		Transport.send(message);
		System.out.println("Mail Sent");
	}
	
	private static Message prepareMessage(Session session, String myAccount, Registration theRegistration) {
		
	
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(theRegistration.getStudent_email()));
			message.setSubject("Event Registered	");
			//message.setContent(html, "text/html");
			 MimeMultipart multipart = new MimeMultipart("related");
			 BodyPart messageBodyPart = new MimeBodyPart();
			 String htmlText = "<div><div style=\"width:60%;display:inline-block;vertical-align:middle;\"><br/><p>Hi "+ theRegistration.getStudent_last()+",</p><br/><div> <p>This is to confirm that You have sucessfully registerd to "+theRegistration.getEvent_name()+"</p><p>Please attend the " +theRegistration.getStudent_first()+" venue on ";
			 htmlText+="05-05-2022 .</p><br/><br/> Regards,<br/>PFW Admin.</div></div><div style=\"width:40%;display:inline-block;vertical-align:middle;\"><img src=\"cid:image\"></div></div>";
	        messageBodyPart.setContent(htmlText, "text/html");

	        // add it
	        multipart.addBodyPart(messageBodyPart);
	        
	        // second part (the image)
	        messageBodyPart = new MimeBodyPart();
	        DataSource fds = new FileDataSource
	          ("D:/backup-se-backend/PFW_Event_Ticketing_System/src/main/resources/Images/images1.jfif");
	        messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID","<image>");

	        // add it
	        multipart.addBodyPart(messageBodyPart);

	        // put everything together
	        message.setContent(multipart);
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
