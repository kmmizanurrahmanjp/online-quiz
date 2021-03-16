package com.softbd.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softbd.onlinequiz.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer>, ResultRepositoryCustom{

}
