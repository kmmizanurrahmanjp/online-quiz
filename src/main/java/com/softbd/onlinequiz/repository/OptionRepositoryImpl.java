package com.softbd.onlinequiz.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softbd.onlinequiz.entity.Option;

@Repository
@Transactional(readOnly = true)
public class OptionRepositoryImpl implements OptionRepositoryCustom{

	 @PersistenceContext
	 EntityManager entityManager;
	 
	@Override
	public Option selectByQuestionNo(int questionNo) {
		Query query = entityManager.createNativeQuery("SELECT o.* FROM option AS o " +
                "WHERE o.question_no = ?", Option.class);
        query.setParameter(1, questionNo);
		return (Option) query.getSingleResult();
	}

}
