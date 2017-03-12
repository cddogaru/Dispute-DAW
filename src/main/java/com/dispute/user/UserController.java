package com.dispute.user;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.dispute.team.Team;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private UserComponent userComponent;
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

	@RequestMapping(value="/user/{userName}")
	public String teamRequest(Model model, @PathVariable String userName){
		User user = userRepository.findByName(userName);
		model.addAttribute("user", user);
		return "profile";
	}
	
	@RequestMapping("/settings")
	public String settings(Model model){
		
		return "settings";
	}
	
	@RequestMapping("/profileSettings")
	public String profileSettings(Model model){
		
		return "settings2";
	}
	
	@RequestMapping("/profileAccounts")
	public String profileAccounts(Model model){
		
		return "settings3";
	}
	
	@RequestMapping(value = "/profileSettings", method = RequestMethod.POST)
	public String profileSettingsChanges(Model model, @RequestParam String nickName, @RequestParam String email, @RequestParam String password, @RequestParam("pic") MultipartFile file){
		User user = userComponent.getLoggedUser();
		
		
		if (!file.isEmpty()) {
			String fileName = user.getName() + ".jpg";
			try {
				File filesFolder = new File("files");
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);
				user.setAvatar(user.getName());

			} catch (Exception e) {
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
			}
		}
		
		if(nickName!=""){
			user.setNickName(nickName);
		}
		if(email!=""){
			user.setEmail(email);
		}
		if(password!=""){
			user.setPassword(password);
		}
		userRepository.save(user);
		return "settings2";
	}
}