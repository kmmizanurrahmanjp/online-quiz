package com.softbd.onlinequiz.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softbd.onlinequiz.entity.Result;

@Repository
@Transactional(readOnly = true)
public class ResultRepositoryImpl implements ResultRepositoryCustom{

	@PersistenceContext
	 EntityManager entityManager;
	
	@Override
	public Result selectByUserId(int userId) {
		Result res = new Result();
		Query query = entityManager.createNativeQuery("SELECT r.* FROM result AS r " +
                "WHERE r.user_id = ?", Result.class);
        query.setParameter(1, userId);
        try {
        	res = (Result) query.getSingleResult();
        }catch (NoResultException nre){
        	res = null;
        }
		return res;
	}

}
