package com.org.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendEmailTLS {

	public static void main(String[] args) {

		String port = "587";
		String email = "kkapre08@gmail.com";
		
		if(args!=null && args.length>0) {
			port = args[0];
			
		}
		if(args!=null && args.length>1) {
			email = args[1];
		}
		
		System.out.println("Port number "+port);
		System.out.println("Email address "+email);

		final String username = "lspevaluator@gmail.com";
		final String password = "****";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		
		//465
		
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); //TLS
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(
					Message.RecipientType.TO,
					InternetAddress.parse(email)
					);
			message.setSubject("Testing Gmail TLS");
			message.setText("Dear Mailer,"
					+ "\n\n I am the email you just triggered!!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
