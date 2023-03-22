package com.XcitEducationFoundations.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.XcitEducationFoundations.Entity.CurrentUserSession;
import com.XcitEducationFoundations.Entity.UserDTO;
import com.XcitEducationFoundations.Exception.UserException;
import com.XcitEducationFoundations.Repo.CurrentUserSessionRepo;
import com.XcitEducationFoundations.Repo.UserRepo;

import antlr.collections.List;
import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LogInService {
	@Autowired
	private CurrentUserSessionRepo currRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public CurrentUserSession getSessionByUuid(String uuid) throws UserException {
		// TODO Auto-generated method stub
		CurrentUserSession curr = currRepo.findByUuid(uuid);
		if (curr != null)
			return curr;
		else
			throw new UserException("Please Enter a Uuid for a User");
	}

	@Override
	public CurrentUserSession loginUser(UserDTO dto) throws UserException {
		// TODO Auto-generated method stub
		UserDTO existingUser = userRepo.findByEmail(dto.getEmail());
		java.util.List<CurrentUserSession> arr = currRepo.findAll();

		if (arr.size() != 0) {
			throw new UserException("Please first logout");
		}

		if (existingUser == null) {
			throw new UserException("Please Enter a valid email Id");
		} else if (existingUser.getPassword().equals(dto.getPassword())) {
			String key = RandomString.make(8);

			CurrentUserSession currentUserSession = new CurrentUserSession();
			currentUserSession.setUuid(key);
			currentUserSession = currRepo.save(currentUserSession);
			return currentUserSession;

		} else {
			throw new UserException("Please Enter a Valid Password");
		}

	}

	@Override
	public Boolean signOutUser(String key) throws UserException {
		// TODO Auto-generated method stub
		CurrentUserSession validUserSession = currRepo.findByUuid(key);

		if (validUserSession == null) {
			throw new UserException("That key doesn't exits");
		}
		currRepo.delete(validUserSession);
		return true;
	}

	@Override
	public Boolean signUpUser(UserDTO dto) throws UserException {
		// TODO Auto-generated method stub
		UserDTO existingUser = userRepo.findByEmail(dto.getEmail());
		if (existingUser != null)

			throw new UserException("Please Enter a Valid Password");
		else
			userRepo.save(dto);
		return true;
	}

}
