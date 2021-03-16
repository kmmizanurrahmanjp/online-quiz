package com.softbd.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softbd.onlinequiz.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
