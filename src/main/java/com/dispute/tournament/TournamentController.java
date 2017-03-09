package com.dispute.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TournamentController {

	@Autowired
	private TournamentRepository tournamentRepository;
	
	@RequestMapping(value = "/tournaments")
	public String tournaments(Model model){
		model.addAttribute("tournaments", tournamentRepository.findAll());
		return "tournaments";
	}
}
