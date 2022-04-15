package com.project.honeycombi.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.Answer;
import com.project.honeycombi.model.Honey;
import com.project.honeycombi.model.User;
import com.project.honeycombi.model.Vegan;
import com.project.honeycombi.repository.AnswerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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

        answer.setVegan(vegan);

        answerRepository.save(answer);

        return "redirect:/vegan/detail?vId="+vId;
    }
    

    @PostMapping("/hanswer/create")
    public String hanswercreate(String content, long hId, HttpSession session) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(new Date());

        Honey honey = new Honey();
        honey.setHId(hId);
 
       User user = (User) session.getAttribute("user");
        answer.setUser(user);

        answer.setHoney(honey);

        answerRepository.save(answer);

        return "redirect:/honey/detail?hId="+hId;
    }
}
