package org.sid.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.entities.dao.AppRoleRepository;
import org.sid.entities.dao.AppUserRepository;
import org.sid.service.AccountService;
import org.sid.util.AppConstant;
import org.sid.util.FunctionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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

	List<AppUser> users = new ArrayList<AppUser>();

	@Override
	public AppUser saveUser(AppUser user) {
		logger.info("Execute - saving user");
		AppUser userApp = appUserRepository.findByUsername(user.getUsername());
		if (userApp != null)
			throw new RuntimeException("User already exists");
		appUserRepository.save(user);
		addRoleToUser(user.getUsername(), AppConstant.User);
		return user;
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
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findByRoleName(rolename);
		logger.info("info - adding role : " + rolename);
		appUser.getRoles().add(appRole);

	}

	@Override
	@Cacheable(value="users", key = "#root.target.MY_KEY")
	public List<AppUser> findAll() {
		this.users = appUserRepository.findAll();
		FunctionUtils.simulateSlowCall();
		return this.users;
	}
	

}
