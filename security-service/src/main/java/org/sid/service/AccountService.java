package org.sid.service;

import java.util.List;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

public interface AccountService {
	
	
	public AppUser saveUser(AppUser user);
	public AppRole save(AppRole role);
	public AppUser loadUserByUsername(String username);
	public void addRoleToUser(String username , String rolename);
	public List<AppUser> findAll();
	

}
