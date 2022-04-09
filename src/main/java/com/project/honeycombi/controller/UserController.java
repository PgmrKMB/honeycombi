package com.project.honeycombi.controller;

import com.project.honeycombi.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {  



    @GetMapping("/sign")
    public String signForm() {
        return "sign";
    }

    @PostMapping(value ="/signin")
    public String signin(){

        

        return "redirect:/main";
    }
    
    

    @PostMapping(value="/signup")
    public String signup(@ModelAttribute User user) {
        
        
        return "redirect:/singin";
    }
    
    

}
