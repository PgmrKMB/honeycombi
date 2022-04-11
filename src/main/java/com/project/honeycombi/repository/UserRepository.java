package com.project.honeycombi.repository;

import java.util.Optional;

import com.project.honeycombi.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPwd(String email, String pwd);

    
}
