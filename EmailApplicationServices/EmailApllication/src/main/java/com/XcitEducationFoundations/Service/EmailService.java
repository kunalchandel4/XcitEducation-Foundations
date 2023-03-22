package com.XcitEducationFoundations.Service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import com.XcitEducationFoundations.Entity.EmailBody;

public interface EmailService {
	public String sendTextToEmail(EmailBody body) throws MessagingException;

	public String sendTextWithAttachmentEmail(EmailBody mainBody, MultipartFile file) throws MessagingException, IOException;
}
