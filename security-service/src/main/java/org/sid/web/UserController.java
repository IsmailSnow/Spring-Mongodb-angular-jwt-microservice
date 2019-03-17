package org.sid.web;

import java.util.List;

import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.sid.util.dto.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private AppUserRepository userRepo;

	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm) {
		logger.info("Execute - Controller register");
		return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
	}

	@GetMapping("/user")
	public AppUser getCurrentUser() {
		logger.info("Execute - Controller register");
		return userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@GetMapping("/users")
	public List<AppUser> getUsers() {
		logger.info("Execute - Controller register");
		return accountService.findAll();
	}

}
