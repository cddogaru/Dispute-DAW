package com.dispute;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dispute.model.*;

@Controller
public class WebController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@RequestMapping(value = {"", "index"} )
	public String index(Model model){
		return "index";
	}
	
	@RequestMapping(value = "/tournaments")
	public String tournaments(Model model){
		model.addAttribute("tournaments", tournamentRepository.findAll());
		return "tournaments";
	}
}
