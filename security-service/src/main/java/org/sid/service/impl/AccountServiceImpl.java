package org.sid.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.sid.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@CacheConfig(cacheNames = { "users" })
public class AccountServiceImpl implements AccountService {
	private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	public static final String MY_KEY = "mykey";

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private AppRoleRepository appRoleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	List<AppUser> users = new ArrayList<AppUser>();

	@Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
		logger.info("Execute - saving user");
		AppUser user = appUserRepository.findByUsername(username);
		if (user != null)
			throw new RuntimeException("User already exists");
		if (!password.equals(confirmedPassword))
			throw new RuntimeException("Please confirm your password");
		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setActived(true);
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUserRepository.save(appUser);
		addRoleToUser(username, AppConstant.User);
		return appUser;
	}

	@Override
	public AppRole save(AppRole role) {
		logger.info("info : save role");
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		logger.info("info : get user by username");
		return appUserRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		// context is transactional , so hibernate will detect the of in the collection
		// and persist to the db
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findByRoleName(rolename);
		logger.info("info - adding role : " + rolename);
		appUser.getRoles().add(appRole);

	}

	@Override
	@Cacheable(value="users", key = "#root.target.MY_KEY")
	public List<AppUser> findAll() {
		this.users = appUserRepository.findAll();
		simulateSlowCall();
		return this.users;
	}
	
	 private void simulateSlowCall() {
	        try {
	            Thread.sleep(3000L);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

}
