package com.dispute.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@RequestMapping(value = "/teams")
	public String teams(Model model){
		
		model.addAttribute("teams", teamRepository.findAll());
		return("teams");
	}
}
