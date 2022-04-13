package com.project.honeycombi.service;

import java.util.List;
import java.util.Optional;

import com.project.honeycombi.model.Mania;
import com.project.honeycombi.model.User;
import com.project.honeycombi.repository.ManiaRepository;
import com.project.honeycombi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ManiaRepository maniaRepository;

    @Override
    public void signup(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> signin(User user) {
      
       Optional<User> opt = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
       
        return opt;
    }

    @Override
    public User usercheck(String email) {
      User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public List<Mania> myWriting(User user) {
        List<Mania> opt = maniaRepository.findByUser_uId(user.getUId());

        return opt;
    }
    
}
