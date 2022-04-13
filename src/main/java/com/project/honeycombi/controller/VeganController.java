package com.project.honeycombi.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.User;
import com.project.honeycombi.model.Vegan;
import com.project.honeycombi.model.VeganFile;
import com.project.honeycombi.repository.VeganFileRepository;
import com.project.honeycombi.repository.VeganRepository;
import com.project.honeycombi.service.VeganService;

@Controller
public class VeganController {

	@Autowired
	VeganRepository veganRepository;

	@Autowired
	VeganService veganService;

	@Autowired
	VeganFileRepository veganFileRepository;

	@GetMapping("/vegan/create")
	public String VeganCreate() {
		return "vegan_create";
	}

	@PostMapping("/vegan/create")
	public String veganCreatePost(@ModelAttribute Vegan vegan, HttpSession session,
			MultipartHttpServletRequest mRequest) {
				
		User user = (User) session.getAttribute("user");
		vegan.setUser(user);
		vegan.setCreateDate(new Date());
		veganRepository.save(vegan);

		Iterator<String> iter = mRequest.getFileNames();
		while (iter.hasNext()) {
			String inputName = iter.next();
			List<MultipartFile> mFiles = mRequest.getFiles(inputName);
			for (MultipartFile mFile : mFiles) {
				String oName = mFile.getOriginalFilename();
				if (oName == null || oName.equals("")) {
					break;
				}

				File f = new File("c:/project" + oName);
				String nName = "";
				if (f.isFile()) {
					String fileName = oName.substring(0, oName.lastIndexOf("."));
					String fileExt = oName.substring(oName.lastIndexOf("."));
					nName = fileName + System.currentTimeMillis() + fileExt;
				} else {
					nName = oName;
				}

				try {
					VeganFile vf = new VeganFile();
					vf.setOriginalFileName(oName);
					vf.setSaveFileName(nName);
					vf.setVegan(vegan);
					veganFileRepository.save(vf);

					mFile.transferTo(new File("c:/project/" + oName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

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

	@GetMapping("vegan/update")
	public String veganUpdate(Long vId, Model model) {

		Optional<Vegan> opt = veganService.update(vId);
		model.addAttribute("vegan", opt.get());

		return "vegan_update";
	}

	@PostMapping("vegan/update")
	public String veganUpdatePost(@ModelAttribute Vegan vegan, Long vId) {
		veganService.updatePost(vId, vegan);

		return "redirect:/vegan/detail?vId=" + vId;
	}

	@GetMapping("/vegandownload")
	public ResponseEntity<Resource> download (@ModelAttribute Vegan vegan) throws Exception {
		List<VeganFile> vList = veganFileRepository.findByVegan(vegan);
		
		String fileName = vList.get(0).getSaveFileName();
		File file = new File("c:/project/" + fileName);

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
		       .header("content-disposition", "filename=" + URLEncoder.encode(file.getName(), "utf-8"))
			   .contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
			   .body(resource);


	}

}
