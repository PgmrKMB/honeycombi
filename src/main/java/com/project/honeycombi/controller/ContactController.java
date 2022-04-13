package com.project.honeycombi.controller;

import com.project.honeycombi.email.Mailer;
import com.project.honeycombi.email.SMTPAuthenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    Mailer mailer;

    @GetMapping("/contact")
    public String contact() {
        return "/contact";
    }

    @PostMapping("/contact")
    public String contact(String email, String subject, String content) {
        Mailer mailer = new Mailer();
        mailer.sendMail(
            "toddlf64@naver.com", 
            "[" + email + "]" + subject, 
            content, 
            new SMTPAuthenticator());
            return "redirect:/contact";
    }
    
}
