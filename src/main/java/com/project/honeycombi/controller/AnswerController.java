package com.project.honeycombi.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.Answer;
import com.project.honeycombi.model.User;
import com.project.honeycombi.model.Vegan;
import com.project.honeycombi.repository.AnswerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @PostMapping("/answer/create")
    public String answercreate(String content, long vId, HttpSession session) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(new Date());

        Vegan vegan = new Vegan();
        vegan.setVId(vId);
 
       User user = (User) session.getAttribute("user");
        answer.setUser(user);

        answerRepository.save(answer);

        return "redirect:/vegan/detail?vId="+vId;
    }
    
}
