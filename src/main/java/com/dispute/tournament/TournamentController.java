package com.dispute.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TournamentController {

	@Autowired
	private TournamentRepository tournamentRepository;
	
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
}
