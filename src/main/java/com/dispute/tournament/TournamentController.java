package com.dispute.tournament;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.dispute.game.Game;
import com.dispute.game.GameRepository;
import com.dispute.participant.Participant;
import com.dispute.user.User;
import com.dispute.user.UserComponent;
import com.dispute.user.UserRepository;

@Controller
public class TournamentController {

	@Autowired
	private TournamentRepository tournamentRepository;
	@Autowired
	private RoundRepository roundRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MatchUpRepository matchUpRepository;
	@Autowired
	private GameRepository gameRepository;
	
	
	@RequestMapping(value = "/tournaments")
	public String tournaments(Model model) {
		model.addAttribute("tournaments", tournamentRepository.findAll());
		return "tournaments";
	}

	@RequestMapping(value = "/myTournaments")
	public String myTournaments(Model model) {
		List<Tournament> tournaments = tournamentRepository
				.findByParticipants_Name(userComponent.getLoggedUser().getName());

		model.addAttribute("tournaments",
				tournamentRepository.findByParticipants_Name(userComponent.getLoggedUser().getName()));
		return "tournaments";
	}

	@RequestMapping(value = "/findTournament", method = RequestMethod.POST)
	public String findTournament(Model model, @RequestParam String name) {
		List<Tournament> tournaments = tournamentRepository.findByNameIgnoreCaseContaining(name);
		model.addAttribute("tournaments", tournaments);
		return "tournaments";
	}

	@RequestMapping(value = "/newTournament")
	public String newTrournament(Model model) {
		List<Game> games = gameRepository.findAll();
		model.addAttribute("games", games);
		return ("newTournament");
	}
	
	@RequestMapping(value = "/newTournament", method = RequestMethod.POST)
	public View addTournament(Model model, @RequestParam String name, @RequestParam String max, @RequestParam String gameName,
			@RequestParam String date, @RequestParam String time, @RequestParam String comment, @RequestParam String mode) {

		Game game = gameRepository.findByName(gameName);
		Tournament tournament = new Tournament(name, comment, Integer.parseInt(max), mode, date, game);
		User user = userRepository.findById(userComponent.getLoggedUser().getId());
		tournament.getAdmins().add(user);
		tournamentRepository.save(tournament);
		return new RedirectView("tournaments.html");
	}

	@RequestMapping(value = "/tournament/{tournamentName}")
	public String tournament(Model model, @PathVariable String tournamentName, @RequestParam(required = false) boolean errorParticipant) {
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		model.addAttribute("tournament", thisTournament);
		model.addAttribute("errorParticipant", errorParticipant);
		if(userComponent.isLoggedUser()){
			User user = userRepository.findById(userComponent.getLoggedUser().getId());
			if(thisTournament.getAdmins().contains(user) || user.getRoles().contains("ROLE_ADMIN")){
				model.addAttribute("admin", true);
			}
			model.addAttribute("userIsInTournament", thisTournament.getParticipants().contains(user));
		}
		
		if(userComponent.isLoggedUser() && thisTournament.isStarted()){
			Round round = thisTournament.getRounds().get(thisTournament.getRounds().size()-1);
			User user = userRepository.findById(userComponent.getLoggedUser().getId());
			for(MatchUp m: round.getMatchUps()){
				if(m.getPlayer1().equals(user) || m.getPlayer2().equals(user)){
					if(!m.isFinished()){
						model.addAttribute("userInMatch", true);
						model.addAttribute("userMatch", m);
						System.out.println(m);
						model.addAttribute("userRound", round);
					}
				}
			}
		}
		return ("tournament");
	}

	@RequestMapping(value = "/tournament/{tournamentName}", method = RequestMethod.POST)
	public View joinTournament(Model model, @PathVariable String tournamentName) {
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		User user = userRepository.findByName(userComponent.getLoggedUser().getName());
		RedirectView rv;
		if(!thisTournament.getParticipants().contains(user)){
			user.getTournaments().add(thisTournament);
			userRepository.save(user);
			rv = new RedirectView("../tournament/" + tournamentName);
		} else {
			rv = new RedirectView("../tournament/" + tournamentName + "?errorParticipant=true");
		}
		
		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping(value = "/tournament/{tournamentName}/startTournament", method=RequestMethod.POST)
	public View startTournament(Model model, @PathVariable String tournamentName) {
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		thisTournament.setStarted(true);
		for(Participant p: thisTournament.getParticipants()){
			thisTournament.getActualParticipants().add(p);
		}
		ArrayList<MatchUp> matchUps = thisTournament.generateMatchUps();
		matchUpRepository.save(matchUps);
		Round round = thisTournament.newRound(matchUps);
		round.setFirstRound(true);
		roundRepository.save(round);
		tournamentRepository.save(thisTournament);
		RedirectView rv = new RedirectView("../../tournament/" + tournamentName);
		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping(value = "/tournament/{tournamentName}/setMatchUp", method=RequestMethod.POST)
	public View setMatchUp(Model model, @PathVariable String tournamentName, @RequestParam Long idMatchUp,
			@RequestParam Long idRound, @RequestParam int result1, @RequestParam int result2) {
		
		Round round = roundRepository.findById(idRound);
		MatchUp matchup = matchUpRepository.findById(idMatchUp);
		Tournament tournament = tournamentRepository.findByName(tournamentName);
		if(result1 != result2){
			matchup.setScore1(result1);
			matchup.setScore2(result2);
			matchup.setFinished(true);
			matchUpRepository.save(matchup);
			
		}
		RedirectView rv = new RedirectView("../../tournament/" + tournamentName);
		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping(value = "/tournament/{tournamentName}/confirmRound", method=RequestMethod.POST)
	public View confirmRound(Model model, @PathVariable String tournamentName, @RequestParam Long idRound){
		Tournament tournament = tournamentRepository.findByName(tournamentName);
		Round round = roundRepository.findById(idRound);
		round.setClosedRound(true);
		for(MatchUp m: round.getMatchUps()){
			tournament.getActualParticipants().remove(m.getLosser());
		}
		if(tournament.getActualParticipants().size()==1){
			tournament.setFinished(true);
			tournamentRepository.save(tournament);
		} else {
			ArrayList<MatchUp> matchUps = tournament.generateMatchUps();
			matchUpRepository.save(matchUps);
			Round newRound = tournament.newRound(matchUps);
			roundRepository.save(newRound);
			tournamentRepository.save(tournament);
		}
		RedirectView rv = new RedirectView("../../tournament/" + tournamentName);
		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping(value = "/tournament/{tournamentName}/newIssue", method = RequestMethod.POST)
	public View newIssue(Model model, @PathVariable String tournamentName, @RequestParam String issue) {
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		User user = userRepository.findByName(userComponent.getLoggedUser().getName());
		thisTournament.getIssues().add(user.getName() + ": " + issue);
		tournamentRepository.save(thisTournament);
		RedirectView rv = new RedirectView("../../tournament/" + tournamentName);
		rv.setExposeModelAttributes(false);
		return rv;
	}
}
