package com.XcitEducationFoundations.Service;

import javax.mail.MessagingException;

import com.XcitEducationFoundations.Entity.EmailBody;

public interface EmailService {
	public String sendTextToEmail(EmailBody body)throws MessagingException;
}
