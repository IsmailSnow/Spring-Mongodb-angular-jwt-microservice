package org.sid.util.converter;

import org.sid.entities.AppUser;
import org.sid.util.dto.UserForm;

public interface UserConverter {
	
	AppUser AppUserFormToUserConverter(UserForm form);

}
