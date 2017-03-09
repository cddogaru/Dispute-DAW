package com.dispute.team;

import java.util.ArrayList;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.dispute.game.Game;

@Controller
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
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
	public RedirectView addTeam(Model model, @RequestParam String name, @RequestParam String acronym, @RequestParam String games, @RequestParam String description){
		Team team = new Team();
		/*ArrayList<Game> gamesList = new ArrayList<>();
		String gnames[] = games.split(",");
		for (String gname : gnames) {
			gamesList.add(/* TO DO*//*);
		}
	*/
		
		team.setAcronym(acronym);
		team.setName(name);
		//team.setGames(gamesList);
		team.setDescription(description);
		
		return new RedirectView("teams.html");
	}
	
	/*
	 @RequestMapping(value = "/changeTeam", method = RequestMethod.POST)
	 public View addUser(@RequestParam String team, @RequestParam String nick, @RequestParam String lastTeam){
	   User user = userRepository.findByNick(nick); 
	   user.setTeam(teamRepository.findByName(team));
	   userRepository.save(user);
	   return new RedirectView("/");
	 }
	 
	 */
}
