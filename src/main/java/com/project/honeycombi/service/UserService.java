package com.project.honeycombi.service;

import java.util.List;
import java.util.Optional;

import com.project.honeycombi.model.Mania;
import com.project.honeycombi.model.User;

public interface UserService {

    public void signup(User user);

    public Optional<User> signin(User user);

    public User usercheck(String email);

    public List<Mania> myWriting(User user);

    public void deleteAcc(User user);
    
}
