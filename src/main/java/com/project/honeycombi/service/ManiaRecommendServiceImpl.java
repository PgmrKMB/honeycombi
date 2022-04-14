package com.project.honeycombi.service;


import com.project.honeycombi.repository.ManiaRecommendRepository;
import com.project.honeycombi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManiaRecommendServiceImpl implements ManiaRecommendService {
    
    @Autowired
    ManiaRecommendRepository maniaRecommendRepository;

    @Autowired
    UserRepository userRepository;
    


}
