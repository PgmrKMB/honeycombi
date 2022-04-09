package com.project.honeycombi.service;

import java.util.Optional;


import com.project.honeycombi.model.User;
import com.project.honeycombi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void singup(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> signin(User user) {
      
       Optional<User> opt = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
       
        return opt;
    }
    
}
