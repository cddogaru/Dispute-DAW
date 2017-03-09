package com.dispute.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	
	@RequestMapping(value = "/user")
	public String teams(Model model){
		
		model.addAttribute("users", UserRepository.findAll());
		return("users");
	}
}
