package com.softbd.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softbd.onlinequiz.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom{

}
