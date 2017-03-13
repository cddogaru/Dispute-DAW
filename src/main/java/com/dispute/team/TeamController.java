package com.dispute.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import com.dispute.user.UserComponent;
import com.dispute.user.UserRepository;

@Controller
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping(value = "/teams")
	public String teams(Model model){
		
		model.addAttribute("teams", teamRepository.findAll());
		return("teams");
	}
	
	@RequestMapping(value = "/newTeam")
	public String newTeam(Model model){
		return("newTeam");
	}
	
	@RequestMapping(value = "/newTeam", method = RequestMethod.POST)
	public View addTeam(Model model, @RequestParam String name, @RequestParam String acronym, @RequestParam String games, @RequestParam String description){
		Team team = new Team(name, acronym, description);
		
		teamRepository.save(team);

		return new RedirectView("teams.html");
	}
	
	@RequestMapping(value="/team/{teamName}")
	public String teamRequest(Model model, @PathVariable String teamName){
		Team team = teamRepository.findByName(teamName);
		model.addAttribute("team", team);
		return "team";
	}
}
