package com.softbd.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softbd.onlinequiz.entity.User;
import com.softbd.onlinequiz.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User insert(User e){
		return userRepository.save(e);
	}
	
	/*@Override
	public User update(int id,User e){
		Optional<User> empChack = userRepository.findById(id);
		if(!empChack.isPresent()) {
			return null;
		}
		e.setUserId(id);
		return userRepository.save(e);
	}
	
	@Override
	public boolean delete(int id){
		Optional<User> empChack = userRepository.findById(id);
		if(!empChack.isPresent()) {
			return false;
		}else {
			userRepository.deleteById(id);
			return true;
		}
	}
	
	@Override
	public List<User> selectAll(){
		return userRepository.findAll();
	}
	
	@Override
	public User selectById(int id){
		Optional<User> empChack = userRepository.findById(id);
		if(!empChack.isPresent()) {
			return null;
		}else {
			return empChack.get();
		}
	}*/

	@Override
	public User selectActiveUser() {
		return userRepository.selectActiveUser();
	}

	@Override
	public User login(int id, String password) {
		return userRepository.login(id, password);
	}
	
}
