package com.dispute.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/user")
	public String teams(Model model){
		model.addAttribute("users", userRepository.findAll());
		return("users");
		
		// to change cause you only wanna your user tho
	}
	
	@RequestMapping(value ="/signup")
	public String newUser(Model model){
		return("signup");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST )
	public View addTeam(Model model, @RequestParam String name, @RequestParam String email, @RequestParam String username, @RequestParam String password){
		User user = new User(username, name, email, password, "USER");
		userRepository.save(user);
		return new RedirectView("teams.html");
	}

}