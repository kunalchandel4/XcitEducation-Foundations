package com.XcitEducationFoundations.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.XcitEducationFoundations.Entity.EmailBody;

@Service
public class EmailServiceImpl implements EmailService {

	/*
	 * This Method implement the text to the email By using java Mail Api It is very
	 * secure and Reusable APi
	 * 
	 * @Body contains
	 * 
	 * @Param mail , @Param password to AuthenticateO
	 * 
	 * @ Param subject of the message
	 * 
	 * @ Param msg contains body of the message
	 * 
	 * @ Param to The Next Address of the email
	 */
	public String sendTextToEmail(EmailBody body) throws MessagingException {

		/*
		 * smtp refer to Simple Mail Transfer Protocol
		 */
		String hostOfGmail = "smtp.gmail.com";

		Properties properties = System.getProperties();

		/*
		 * some mandatory configuration to the properties Object ;
		 * 
		 * It will contain Key : value Format
		 */

		properties.put("mail.smtp.host", hostOfGmail);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.host", "true");

		/*
		 * Session Object Have Factory Method
		 * 
		 * Step *
		 */
		javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				/*
				 * @Param A Current User Mail
				 * 
				 * @Param B Current User Password
				 */
				return new PasswordAuthentication(body.getMail(), body.getPassword());
			}

		});

		/*
		 * Any Debug
		 */
		session.setDebug(true);

		/*
		 * Compose The message MimeMessage
		 * 
		 * Step **
		 */
		MimeMessage message = new MimeMessage(session);

		/*
		 * Sender Email Address
		 */
		message.setFrom(body.getMail());

		/*
		 * Next Email Address Where the mail Have to go
		 */
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(body.getTo()));
		/*
		 * Subject Body
		 */

		message.setSubject(body.getSubject());
		/*
		 * Main Body
		 */
		message.setText(body.getMsg());

		// sending...

		Transport.send(message);

		return "Mail Send Succesfully ";

	}

	/*
	 * This Method implement the Attachment to the email By using java Mail Api It
	 * is very secure and Reusable APi
	 * 
	 * @ Param subject of the message
	 * 
	 * @ Param msg contains body of the message
	 * 
	 * @ Param to The Next Address of the email
	 */

	public String sendTextWithAttachmentToEmail(String subject, String msg, String to) throws MessagingException {

		/*
		 * smtp refer to Simple Mail Transfer Protocol
		 */
		String hostOfGmail = "smtp.gmail.com";

		Properties properties = System.getProperties();

		/*
		 * some mandatory configuration to the properties Object ;
		 * 
		 * It will contain Key : value Format
		 */

		properties.put("mail.smtp.host", hostOfGmail);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.host", "true");

		/*
		 * Session Object Have Factory Method
		 * 
		 * Step *
		 */
		javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				/*
				 * @Param A Current User Mail
				 * 
				 * @Param B Current User Password
				 */
				return new PasswordAuthentication("mailId", "password");
			}

		});

		/*
		 * Any Debug
		 */
		session.setDebug(true);

		/*
		 * Compose The message MimeMessage
		 * 
		 * Step **
		 */
		MimeMessage message = new MimeMessage(session);

		/*
		 * Sender Email Address
		 */
		message.setFrom("");

		/*
		 * Next Email Address Where the mail Have to go
		 */
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		/*
		 * Subject Body
		 */

		message.setSubject(subject);
		/*
		 * Main Body
		 */
//		message.setText(msg);

		// sending...

		Transport.send(message);

		return "Mail Send Succesfully ";

	}

}
