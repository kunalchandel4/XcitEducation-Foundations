package com.XcitEducationFoundations.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.XcitEducationFoundations.Entity.CurrentUserSession;

public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findByUuid(String uuid);

}