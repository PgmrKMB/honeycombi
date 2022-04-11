package com.project.honeycombi.service;

import java.util.Optional;

import com.project.honeycombi.model.User;

public interface UserService {

    public void signup(User user);

    public Optional<User> signin(User user);
    
}
