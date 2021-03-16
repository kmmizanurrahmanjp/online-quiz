package com.softbd.onlinequiz.repository;

import com.softbd.onlinequiz.entity.User;

public interface UserRepositoryCustom {

	public User selectActiveUser();
	public User login(int id, String password);
}
