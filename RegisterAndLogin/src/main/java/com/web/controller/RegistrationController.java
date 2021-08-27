package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dto.RegistrationDTO;
import com.web.service.IUserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private IUserService userService;
	
	// (1) giống (2)
	@ModelAttribute("user") // (1)
	public RegistrationDTO  registrationDTO() {
		return new RegistrationDTO();
	}	
//	public String showRegistrationForm(Model model) {  
//		model.addAttribute("user", new RegistrationDTO()); 	// (2)
//		return "registration";
//	}
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") RegistrationDTO registrationDTO) {
		userService.save(registrationDTO);
		return "redirect:/registration?success";
	}
}
