package com.softbd.onlinequiz.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softbd.onlinequiz.entity.Answer;

@Repository
@Transactional(readOnly = true)
public class AnswerRepositoryImpl implements AnswerRepositoryCustom{

	@PersistenceContext
	 EntityManager entityManager;
	
	@Override
	public Answer selectByIdAndAnswer(int id, String answer) {
		Answer ans = new Answer();
		Query query = entityManager.createNativeQuery("SELECT a.* FROM answer AS a " +
                "WHERE a.question_no = ? AND a.answer = ?", Answer.class);
        query.setParameter(1, id);
        query.setParameter(2, answer);
        try {
        	ans = (Answer) query.getSingleResult();
        }catch (NoResultException nre){
        	ans = null;
        }
		
		
		return ans;
	}

}
