package com.project.honeycombi.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.Mania;
import com.project.honeycombi.service.ManiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    @GetMapping(value = "/mania/write")
    public String maniaWriteForm() {

        return "mania_write";
    }

    @PostMapping(value = "/mania/write")
    public String maniaWritePost(
        @ModelAttribute Mania mania,
        HttpSession session,
        MultipartHttpServletRequest mRequest
        ) {

        maniaService.ManiaWrite(mania, session, mRequest);


        return "redirect:/mania";
    }

    @GetMapping(value = "/mania/detail")
    public String maniaDetail(Model model, Long mId) {

        Optional<Mania> opt = maniaService.maniaDetail(mId);

        

        model.addAttribute("mania", opt.get());

        return "mania_detail";
    }

}
