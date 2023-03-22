package com.XcitEducationFoundations.Entity;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.ToString;

@Validated
@Data
@ToString
public class EmailBody {
	@Email
	@NotBlank
	@NotEmpty
	private String mail;
	@NotBlank
	@NotEmpty
	@NotNull
	private String password;
	@NotBlank
	@NotEmpty
	@NotNull
	private String subject;
	@NotBlank
	@NotEmpty
	@NotNull
	private String msg;
	@Email
	@NotNull
	@NotBlank
	@NotEmpty
	private String to;

}
