package com.project.honeycombi.controller;

import java.util.List;

import com.project.honeycombi.model.Honey;
import com.project.honeycombi.model.Mania;
import com.project.honeycombi.model.Vegan;
import com.project.honeycombi.service.HoneyService;
import com.project.honeycombi.service.ManiaService;
import com.project.honeycombi.service.VeganService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    HoneyService honeyService;

    @Autowired
    VeganService veganService;

    @Autowired
    ManiaService maniaService;

    @GetMapping(value = "/main")
    public String main(Model model) {

        List<Honey> hlist=honeyService.list();
        model.addAttribute("hlist", hlist);

        List<Mania> mlist=maniaService.list();
        model.addAttribute("mlist", mlist);

        List<Vegan> vlist=veganService.list();
        model.addAttribute("vlist", vlist);

        


        return "index";
    }

}
