package com.XcitEducationFoundations.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.XcitEducationFoundations.Entity.UserDTO;
import com.XcitEducationFoundations.Exception.UserException;

public interface UserRepo extends JpaRepository<UserDTO, Integer>{
	public UserDTO findByEmail(String email) throws UserException;
}
