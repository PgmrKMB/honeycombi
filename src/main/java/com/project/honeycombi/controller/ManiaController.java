package com.project.honeycombi.controller;

import java.util.List;

import com.project.honeycombi.model.Mania;
import com.project.honeycombi.service.ManiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManiaController {

    @Autowired
    ManiaService maniaService;

    @GetMapping(value = "/mania")
    public String mania(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {

        List<Mania> list = maniaService.maniaList(page);

        model.addAttribute("list", list);

        System.out.println(list.toString());

        return "mania_list";
    }

}
