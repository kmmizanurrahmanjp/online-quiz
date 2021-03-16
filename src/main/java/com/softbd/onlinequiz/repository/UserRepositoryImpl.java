package com.softbd.onlinequiz.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softbd.onlinequiz.entity.User;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositoryCustom{

	@PersistenceContext
	 EntityManager entityManager;
	
	@Override
	public User selectActiveUser() {
		User user = new User();
		Query query = entityManager.createNativeQuery("SELECT u.* FROM user AS u " +
                "WHERE u.login_flag = ?", User.class);
        query.setParameter(1, 1);
        try {
        	user = (User) query.getSingleResult();
        }catch (NoResultException nre){
        	user = null;
        }
		return user;
	}

	@Override
	public User login(int id, String password) {
		User user = new User();
		Query query = entityManager.createNativeQuery("SELECT u.* FROM user AS u " +
                "WHERE u.user_id = ? AND u.password = ?", User.class);
        query.setParameter(1, id);
        query.setParameter(2, password);
        try {
        	user = (User) query.getSingleResult();
        }catch (NoResultException nre){
        	user = null;
        }
		return user;
	}

}
