package org.sid.web;

import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.sid.util.dto.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm) {
		logger.info("Execute - Controller register");
		return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
	}

}
