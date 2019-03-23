package org.sid.util.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserForm {

	@NotBlank(message = "email is mandatory")
	private String username;
	@NotBlank(message = "password is mandatory")
    private String password;
    private String confirmedPassword;
}
