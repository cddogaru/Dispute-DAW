package com.dispute.tournament;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.dispute.user.User;
import com.dispute.user.UserComponent;
import com.dispute.user.UserRepository;

@Controller
public class TournamentController {

	@Autowired
	private TournamentRepository tournamentRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/tournaments")
	public String tournaments(Model model){
		model.addAttribute("tournaments", tournamentRepository.findAll());
		return "tournaments";
	}
	
	@RequestMapping(value = "/newTournament") 
	public String newTrournament(Model model){
		return("newTournament");
	}
	

	@RequestMapping(value = "/newTournament", method = RequestMethod.POST )
	public View addTournament(Model model, @RequestParam String name, @RequestParam String url, @RequestParam boolean registration, @RequestParam String max, @RequestParam String game, @RequestParam String time, @RequestParam String comment){
		
		int	maxPlyrs = 60;
		
		String dmode = "List";
		Tournament tournament = new Tournament(name, comment, maxPlyrs, dmode, time);
		tournamentRepository.save(tournament);
		return new RedirectView("tournaments.html");
	}
	
	@RequestMapping(value = "/tournament/{tournamentName}")
	 	public String tournament(Model model, @PathVariable String tournamentName){
	 		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
	 		model.addAttribute("tournament" ,thisTournament);
	 		return("tournament");
	 	}
	 	
	 	@RequestMapping(value = "/tournament/{tournamentName}", method = RequestMethod.POST)
	 	public View joinTournament(Model model, @PathVariable String tournamentName){
	 		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
	 		User user = userRepository.findByName(userComponent.getLoggedUser().getName());
	 		
	 		user.getTournaments().add(thisTournament);
	 		userRepository.save(user);
	 		RedirectView rv = new RedirectView("../tournament/" + tournamentName + "?error=true");
	 		rv.setExposeModelAttributes(false);
	 		return rv;
	 	}
}
