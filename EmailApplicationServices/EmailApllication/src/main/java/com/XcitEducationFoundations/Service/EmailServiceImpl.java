package com.XcitEducationFoundations.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.XcitEducationFoundations.Entity.EmailBody;

@Service
public class EmailServiceImpl implements EmailService {
	public static void setRecipients(Message message, Object addresslist) throws AddressException, MessagingException {
		if (addresslist instanceof String) { // CharSequence
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse((String) addresslist));
		} else if (addresslist instanceof String[]) { // String[] « Array with collection of Strings/
			String[] toAddressList = (String[]) addresslist;
			InternetAddress[] mailAddress_TO = new InternetAddress[toAddressList.length];
			for (int i = 0; i < toAddressList.length; i++) {
				mailAddress_TO[i] = new InternetAddress(toAddressList[i]);
			}
			message.setRecipients(Message.RecipientType.TO, mailAddress_TO);
		}
	}

	/*
	 * This Method implement the text to the Many email By using java Mail Api It is
	 * very secure and Reusable APi
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
	public String sendTextToManyEmail(EmailBody body) throws MessagingException {

		/*
		 * smtp refer to Simple Mail Transfer Protocol
		 */
		String hostOfGmail = "smtp.gmail.com";

		Properties props = System.getProperties();

		/*
		 * some mandatory configuration to the properties Object ;
		 * 
		 * It will contain Key : value Format
		 */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		/*
		 * Session Object Have Factory Method
		 * 
		 * Step *
		 */

		javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(body.getMail(), body.getPassword());

			}

		});

		/*
		 * Any Debug
		 */
//		session.setDebug(true);

		/*
		 * Compose The message MimeMessage
		 * 
		 * Step **
		 */

		MimeMessage message = new MimeMessage(session);

		/*
		 * Sender Email Address
		 */ String toBody = body.getTo();
		String[] stringArr = toBody.split(",");
//
//			InternetAddress[] internetAdd = InternetAddress.parse(toBody);

//		for(int i=0 ; i<stringArr.length ; i++) {
//			internetAdd[i] = new InternetAddress(stringArr[i].trim());
//		}
//		
//		

		/*
		 * Next Email Address Where the mail Have to go
		 * 
		 * pass the list of InternetAddress
		 */
//message.setFrom(toBody);

		setRecipients(message, stringArr);
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

		Properties props = System.getProperties();

		/*
		 * some mandatory configuration to the properties Object ;
		 * 
		 * It will contain Key : value Format
		 */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		/*
		 * Session Object Have Factory Method
		 * 
		 * Step *
		 */

		javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(body.getMail(), body.getPassword());

			}

		});

		/*
		 * Any Debug
		 */
//		session.setDebug(true);

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

	// *******************************************************************

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
	@Override
	public String sendTextWithAttachmentEmail(EmailBody body, MultipartFile file)
			throws MessagingException, IOException {

		/*
		 * smtp refer to Simple Mail Transfer Protocol
		 */
		String hostOfGmail = "smtp.gmail.com";

		Properties props = System.getProperties();

		/*
		 * some mandatory configuration to the properties Object ;
		 * 
		 * It will contain Key : value Format
		 */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		/*
		 * Session Object Have Factory Method
		 * 
		 * Step *
		 */

		javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(body.getMail(), body.getPassword());

			}

		});

		/*
		 * Any Debug
		 */
//		session.setDebug(true);

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

		// 3) create MimeBodyPart object and set your message text
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(body.getMsg());

		// 4) create new MimeBodyPart object and set DataHandler object to this object
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		System.out.println(file.getClass());

		File convert = new File("C:\\Users\\kunal\\OneDrive\\Desktop\\ParticeRepo\\Documents\\",
				file.getOriginalFilename());

		convert.createNewFile();

		FileOutputStream write = new FileOutputStream(convert);
		write.write(file.getBytes());

		// STore your Local Device
		String filename = "C:\\Users\\kunal\\OneDrive\\Desktop\\ParticeRepo\\Documents\\" + file.getOriginalFilename();
		DataSource source = new FileDataSource(filename);
		messageBodyPart2.setDataHandler(new DataHandler(source));
		messageBodyPart2.setFileName(filename);

		// 5) create Multipart object and add MimeBodyPart objects to this object
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);

		// 6) set the multiplart object to the message object
		message.setContent(multipart);

		// sending...

		Transport.send(message);

		return "Mail Send Succesfully ";

	}

	@Override
	public String sendTextWithAttachmentToManyEmail(EmailBody body, MultipartFile file)
			throws MessagingException, IOException {

		/*
		 * smtp refer to Simple Mail Transfer Protocol
		 */
		String hostOfGmail = "smtp.gmail.com";

		Properties props = System.getProperties();

		/*
		 * some mandatory configuration to the properties Object ;
		 * 
		 * It will contain Key : value Format
		 */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		/*
		 * Session Object Have Factory Method
		 * 
		 * Step *
		 */

		javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(body.getMail(), body.getPassword());

			}

		});

		/*
		 * Any Debug
		 */
//		session.setDebug(true);

		/*
		 * Compose The message MimeMessage
		 * 
		 * Step **
		 */

		MimeMessage message = new MimeMessage(session);

		/*
		 * Sender Email Address
		 */
		/*
		 * Sender Email Address
		 */ String toBody = body.getTo();
		String[] stringArr = toBody.split(",");

		/*
		 * Next Email Address Where the mail Have to go
		 * 
		 * pass the list of InternetAddress
		 */

		setRecipients(message, stringArr);

		/*
		 * Subject Body
		 */

		message.setSubject(body.getSubject());
		/*
		 * Main Body
		 */

		// 3) create MimeBodyPart object and set your message text
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(body.getMsg());

		// 4) create new MimeBodyPart object and set DataHandler object to this object
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		System.out.println(file.getClass());

		File convert = new File("C:\\Users\\kunal\\OneDrive\\Desktop\\ParticeRepo\\Documents\\",
				file.getOriginalFilename());

		convert.createNewFile();

		FileOutputStream write = new FileOutputStream(convert);
		write.write(file.getBytes());
// STore your Local Device
		String filename = "C:\\Users\\kunal\\OneDrive\\Desktop\\ParticeRepo\\Documents\\" + file.getOriginalFilename();
		DataSource source = new FileDataSource(filename);
		messageBodyPart2.setDataHandler(new DataHandler(source));
		messageBodyPart2.setFileName(filename);

		// 5) create Multipart object and add MimeBodyPart objects to this object
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);

		// 6) set the multiplart object to the message object
		message.setContent(multipart);

		// sending...

		Transport.send(message);

		return "Mail Send Succesfully ";

	}

}
