package com.project.honeycombi.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.Mania;
import com.project.honeycombi.model.User;
import com.project.honeycombi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MypageController {
    
    @Autowired
    UserService userService;

    @GetMapping(value="/mypage")
    public String mypage(HttpSession session, Model model) {

       User user =(User) session.getAttribute("user");

       List<Mania> mwList = userService.myWriting(user);

        model.addAttribute("mwlist", mwList);

        return "mypage";
    }


    @PostMapping(value="/deleteacc")
    public String deleteAcc(@ModelAttribute User user, Model model, HttpSession session) {

        Optional<User> opt = userService.signin(user);

        if(opt.isPresent()){
            
            userService.deleteAcc(user);
        
        }else{
            model.addAttribute("errorAcc", "이메일 또는 비밀번호를 잘못 입력하셨습니다.");
        }

        return "redirect:/mypage";
    }
    

}

