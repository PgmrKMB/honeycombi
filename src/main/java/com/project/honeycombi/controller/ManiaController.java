package com.project.honeycombi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.Mania;
import com.project.honeycombi.model.ManiaFile;
import com.project.honeycombi.service.ManiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class ManiaController {

    @Autowired
    ManiaService maniaService;

    @GetMapping(value = "/mania")
    public String mania(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {

        List<Mania> list = maniaService.maniaList(page);
        model.addAttribute("list", list);

        return "/mania/mania_list";
    }

    @GetMapping(value = "/mania/write")
    public String maniaWriteForm() {

        return "/mania/mania_write";
    }

    @PostMapping(value = "/mania/write")
    public String maniaWritePost(
            @ModelAttribute Mania mania,
            HttpSession session,
            MultipartHttpServletRequest mRequest) {

        maniaService.ManiaWrite(mania, session, mRequest);

        return "redirect:/mania";
    }

    @GetMapping(value = "/mania/detail")
    public String maniaDetail(Model model, Long mId) {

        Optional<Mania> opt = maniaService.maniaDetail(mId);

        maniaService.count(mId);

        model.addAttribute("mania", opt.get());

        return "/mania/mania_detail";
    }

    @GetMapping(value = "/maniadownload")
    public ResponseEntity<Resource> download(@ModelAttribute Mania mania) throws Exception {

        List<ManiaFile> fList = maniaService.download(mania);

        String fileName = fList.get(0).getSaveFileName();
        File file = new File("c:/study/" + fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("content-disposition", "filename=" + URLEncoder.encode(file.getName(), "utf-8"))
                .contentLength(file.length())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    @GetMapping(value = "/maniadownload2")
    public ResponseEntity<Resource> download2(@ModelAttribute Mania mania) throws Exception {

        List<ManiaFile> fList = maniaService.download(mania);

        String fileName = fList.get(1).getSaveFileName();
        File file = new File("c:/study/" + fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("content-disposition", "filename=" + URLEncoder.encode(file.getName(), "utf-8"))
                .contentLength(file.length())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    @GetMapping(value = "/maniadownload3")
    public ResponseEntity<Resource> download3(@ModelAttribute Mania mania) throws Exception {

        List<ManiaFile> fList = maniaService.download(mania);

        String fileName = fList.get(2).getSaveFileName();
        File file = new File("c:/study/" + fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("content-disposition", "filename=" + URLEncoder.encode(file.getName(), "utf-8"))
                .contentLength(file.length())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @GetMapping(value = "/mania/delete")
    public String maniaDelete(@RequestParam Long mId) {
        maniaService.delete(mId);
        return "redirect:/mania";
    }

    @GetMapping(value = "/mania/update")
    public String maniaUpdate(@RequestParam Long mId, Model model) {
        Optional<Mania> opt = maniaService.update(mId);

        if (opt.isPresent()) {
            model.addAttribute("mania", opt.get());
        } else {
            model.addAttribute("mupdate_r", "fail");
        }

        return "/mania/mania_update";
    }

    @PostMapping(value = "/mania/update")
    public String maniaUpdatePost(@ModelAttribute Mania mania, Long mId) {
        maniaService.updatePost(mId, mania);

        return "redirect:/mania/detail?mId=" + mId;
    }

    @PostMapping(value = "/mania/recommend")
    @ResponseBody
    public String mrcmd(Long mId, Long uId){
        
        System.out.println(mId);
        System.out.println(uId);

        maniaService.mrcmd(mId,uId);

        return "aaa";
       
    }

}
