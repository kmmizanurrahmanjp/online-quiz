package com.softbd.onlinequiz.service;

import com.softbd.onlinequiz.entity.User;

public interface UserService {

	public User insert(User e);
	/*public User update(int id,User e);
	public boolean delete(int id);
	
	public List<User> selectAll();
	public User selectById(int id);*/
	
	public User selectActiveUser();
	
	public User login(int id, String password);
}
