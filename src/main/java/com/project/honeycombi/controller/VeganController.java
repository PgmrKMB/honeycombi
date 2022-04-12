package com.project.honeycombi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.User;
import com.project.honeycombi.model.Vegan;
import com.project.honeycombi.repository.VeganRepository;
import com.project.honeycombi.service.VeganService;

@Controller
public class VeganController {

	@Autowired
	VeganRepository veganRepository;

	@Autowired
	VeganService veganService;



	@GetMapping("/vegan/create")
	public String VeganCreate() {
		return "vegan_create";
	}

	@PostMapping("/vegan/create")
	public String veganCreatePost(@ModelAttribute Vegan vegan, HttpSession session) {
		User user = (User) session.getAttribute("user");
		vegan.setUser(user);
		vegan.setCreateDate(new Date());
		veganRepository.save(vegan);

		return "redirect:/vegan/list";
	}

	@GetMapping("/vegan/list")
	public String vegan(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {

		List<Vegan> list = veganService.list(page);

		model.addAttribute("list", list);

		return "vegan_list";
	}

	@GetMapping("vegan/detail")
	public String veganDetail(Long vId, Model model) {

		Optional<Vegan> opt = veganService.detail(vId);

		model.addAttribute("vegan", opt.get());

		return "vegan_detail";

	}

	@GetMapping("vegan/delete")
	public String veganDelete(Long vId) {
	veganService.delete(vId);

	return "redirect:/vegan/list";

	}


}
