package com.softbd.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softbd.onlinequiz.entity.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer>, OptionRepositoryCustom{

}
