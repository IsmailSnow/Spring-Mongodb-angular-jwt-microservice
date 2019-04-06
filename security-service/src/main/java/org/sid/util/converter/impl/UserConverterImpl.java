package org.sid.util.converter.impl;

import org.sid.entities.AppUser;
import org.sid.util.converter.UserConverter;
import org.sid.util.dto.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserConverterImpl implements UserConverter {


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AppUser AppUserFormToUserConverter(UserForm form) {
		AppUser user = new AppUser();
		user.setActived(true);
		user.setBirthday(form.getBirthday());
		user.setEmail(form.getEmail());
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
		user.setUsername(form.getUsername());
		return user;
	}

}
