package com.softbd.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softbd.onlinequiz.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>, AnswerRepositoryCustom{

}
