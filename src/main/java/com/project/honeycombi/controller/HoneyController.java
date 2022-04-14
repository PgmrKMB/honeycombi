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

import com.project.honeycombi.model.Honey;
import com.project.honeycombi.model.HoneyFile;
import com.project.honeycombi.model.User;
import com.project.honeycombi.repository.HoneyFileRepository;
import com.project.honeycombi.repository.HoneyRepository;
import com.project.honeycombi.service.HoneyService;

@Controller
public class HoneyController {

	@Autowired
	HoneyRepository honeyRepository;

	@Autowired
	HoneyService honeyService;

	@Autowired
	HoneyFileRepository honeyFileRepository;

	@GetMapping("/honey/create")
	public String HoneyCreate() {
		return "/honey/honey_create";
	}

	@PostMapping("/honey/create")
	public String honeyCreatePost(@ModelAttribute Honey honey, HttpSession session,
			MultipartHttpServletRequest mRequest) {
				
		User user = (User) session.getAttribute("user");
		honey.setUser(user);
		honey.setCreateDate(new Date());
		honeyRepository.save(honey);

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
					HoneyFile vf = new HoneyFile();
					vf.setOriginalFileName(oName);
					vf.setSaveFileName(nName);
					vf.setHoney(honey);
					honeyFileRepository.save(vf);

					mFile.transferTo(new File("c:/project/" + oName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		return "redirect:/honey/list";
	}

	@GetMapping("honey/list")
	public String honey(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {

		List<Honey> list = honeyService.list(page);

		model.addAttribute("list", list);

		return "/honey/honey_list";
	}

	@GetMapping("/honey/detail")
	public String veganDetail(Long hId, Model model) {

		Optional<Honey> opt = honeyService.detail(hId);
		
		honeyService.hit(hId);

		model.addAttribute("honey", opt.get());

		return "/honey/honey_detail";

	}

	@GetMapping("/honey/delete")
	public String honeyDelete(Long hId) {
		honeyService.delete(hId);

		return "redirect:/honey/list";

	}

	@GetMapping("/honey/update")
	public String honeyUpdate(Long hId, Model model) {

		Optional<Honey> opt = honeyService.update(hId);
		model.addAttribute("honey", opt.get());

		return "/honey/honey_update";
	}

	@PostMapping("/honey/update")
	public String honeyUpdatePost(@ModelAttribute Honey honey, Long hId) {
		honeyService.updatePost(hId, honey);

		return "redirect:/honey/detail?hId=" + hId;
	}

	@GetMapping("/honeydownload")
	public ResponseEntity<Resource> download (@ModelAttribute Honey honey) throws Exception {
		List<HoneyFile> hList = honeyFileRepository.findByHoney(honey);
		
		String fileName = hList.get(0).getSaveFileName();
		File file = new File("c:/project/" + fileName);

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
		       .header("content-disposition", "filename=" + URLEncoder.encode(file.getName(), "utf-8"))
			   .contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
			   .body(resource);


	}

}
