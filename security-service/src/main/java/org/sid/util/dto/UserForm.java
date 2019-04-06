package org.sid.util.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.sid.util.validators.PasswordsEqualConstraint;
import org.sid.util.validators.impl.ValidPassword;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@PasswordsEqualConstraint(message = "passwords are not equal")
public class UserForm {

	@NotBlank(message = "email is mandatory")
	private String username;
	
	@Email(message = "lastName is mandatory")
	private String email;
	
	@ValidPassword(message = "Valid password is mandatory")
    private String password;
	
	@NotBlank(message = "Valid confirmation is mandatory")
    private String confirmedPassword;

    @NotBlank(message = "lastName is mandatory")
	private String lastName;

    @NotBlank(message = "firstName is mandatory")
	private String firstName;
	
    @DateTimeFormat(pattern="dd/MMM/yyyy")
	private String birthday;
}
