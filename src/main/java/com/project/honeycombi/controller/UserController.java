package com.project.honeycombi.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.User;
import com.project.honeycombi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/sign")
    public String signForm() {
        return "sign";
    }

    @PostMapping(value = "/signup")
    public String signup(@ModelAttribute User user) {

        userService.signup(user);

        return "redirect:/sign";
    }

    @PostMapping(value = "/signin")
    public String signin(@ModelAttribute User user, HttpSession session) {

        Optional<User> opt = userService.signin(user);

        session.setAttribute("user", opt.get());

        System.out.println(opt.get().getNickname());

        return "redirect:/main";
    }

    @GetMapping("/signout")
    public String signout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

    @GetMapping(value="/user/check")
    @ResponseBody
    public User userCheck(String email) {
        User user = userService.usercheck(email);
        return user;
    }
    
    

}
