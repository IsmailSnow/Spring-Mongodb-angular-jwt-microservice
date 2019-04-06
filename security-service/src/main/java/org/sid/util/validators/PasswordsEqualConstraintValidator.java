package org.sid.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.sid.util.dto.UserForm;

public class PasswordsEqualConstraintValidator implements ConstraintValidator<PasswordsEqualConstraint, UserForm> {

	@Override
	public boolean isValid(UserForm value, ConstraintValidatorContext context) {
		return value.getPassword().equals(value.getConfirmedPassword());
	}

}
