package com.project.honeycombi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.honeycombi.repository.VeganRepository;

@Controller
public class VeganController {
	
	@Autowired
	VeganRepository veganRepository;

	
	@GetMapping("/vegan/create")
	public String VeganCreate() {
		return "vegan_create";
	}
	
	@PostMapping("/vegan/create")
	public String veganCreatePost(
			) {
		return "redirect:/vegan/list";
	}
}
