package com.XcitEducationFoundations.Service;

import com.XcitEducationFoundations.Entity.CurrentUserSession;
import com.XcitEducationFoundations.Entity.UserDTO;
import com.XcitEducationFoundations.Exception.UserException;

public interface LogInService {

	public CurrentUserSession getSessionByUuid(String uuid) throws UserException;

	public Boolean signUpUser(UserDTO dto) throws UserException;

	public CurrentUserSession loginUser(UserDTO dto) throws UserException;

	public String signOutUser(String key) throws UserException;
}
