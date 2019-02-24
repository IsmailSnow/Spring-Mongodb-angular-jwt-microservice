package org.sid.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	
	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Execute : loading user");
		AppUser user = accountService.loadUserByUsername(username);
		if(Objects.isNull(user)) throw new UsernameNotFoundException("invaid user");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		logger.info("info : User app generated");
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
