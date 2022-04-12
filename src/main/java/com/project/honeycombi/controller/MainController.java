package com.project.honeycombi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String main() {
        return "main";
    }

}
