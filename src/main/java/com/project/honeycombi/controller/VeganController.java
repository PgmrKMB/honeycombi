package com.project.honeycombi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.User;
import com.project.honeycombi.model.Vegan;
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
	public String veganCreatePost(@ModelAttribute Vegan vegan,
	HttpSession session) {
		User user = (User) session.getAttribute("user");
		vegan.setUser(user);
		vegan.setCreateDate(new Date());
		veganRepository.save(vegan);
		
		return "redirect:/vegan/create";
	}

	@GetMapping("/vegan/list")
	public String vegan(Model model, @RequestParam(value="page", defaultValue="1") int page){
		Page<Vegan> v = veganRepository.findAll(
			PageRequest.of(page -1, 10, Sort.Direction.DESC, "createDate"));
		List<Vegan> list = v.getContent();

		model.addAttribute("list", list);
System.out.println("==================================================================================================");
System.out.println("==================================================================================================");
		System.out.println(list);
		System.out.println("==================================================================================================");
		System.out.println("==================================================================================================");

		return "vegan_list";
	}
}
