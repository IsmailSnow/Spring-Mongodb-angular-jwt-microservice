package org.sid.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.sid.util.dto.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
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
	public AppUser register(@RequestBody @Valid UserForm userForm,BindingResult result) {
		if(result.hasErrors()) {
			System.out.print(result.getAllErrors());
		}
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
	

//    @PostMapping("/users")
//    ResponseEntity<String> addUser(@Valid @RequestBody UserForm user) {
//        // persisting the user
//        return ResponseEntity.ok("User is valid");
//    }
	
	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
			logger.info("Error Destroying session");
		}
		return "Done";
	}

}
