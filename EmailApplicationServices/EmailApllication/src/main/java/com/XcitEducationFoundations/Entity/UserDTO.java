package com.XcitEducationFoundations.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
@Entity
public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@Email
	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 6, max = 15, message = "please provide the currect password")
	private String password;

}
