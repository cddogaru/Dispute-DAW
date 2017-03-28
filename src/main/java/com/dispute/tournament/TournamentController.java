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
import com.dispute.team.Team;
import com.dispute.team.TeamRepository;
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
	@Autowired
	private TeamRepository teamRepository;

	@RequestMapping(value = "/tournaments")
	public String tournaments(Model model, @RequestParam(required = false) boolean noAdmin) {
		model.addAttribute("tournaments", tournamentRepository.findAll());
		model.addAttribute("noAdmin", noAdmin);
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

	@RequestMapping(value = "/filterTournament", method = RequestMethod.POST)
	public String filterTournament(Model model, @RequestParam String name, @RequestParam String description,
			@RequestParam String game, @RequestParam String mode) {
		List<Tournament> tournaments = tournamentRepository.findAll();
		
		if (name.isEmpty() && description.isEmpty()) {
			for (Tournament tournament : tournaments) {
				if (!(tournament.getGame().getName().equals(game) || tournament.getMode().equals(mode))) {
					tournaments.remove(tournament);
				}
			}
		} else if (name.isEmpty()) {
			for (Tournament tournament : tournaments) {
				if (!(tournament.getDescription().equals(description) || tournament.getGame().getName().equals(game) ||
						tournament.getMode().equals(mode))) {
					tournaments.remove(tournament);
				}
			}
		} else if (description.isEmpty()) {
			for (Tournament tournament : tournaments) {
				if (!(tournament.getName().equals(name) || tournament.getGame().getName().equals(game) || tournament.getMode().equals(mode))) {
					tournaments.remove(tournament);
				}
			}
		} else {
			for (Tournament tournament : tournaments) {
				if (!(tournament.getName().equals(name) || tournament.getDescription().equals(description) ||
						tournament.getGame().getName().equals(game) || tournament.getMode().equals(mode))) {
					tournaments.remove(tournament);
				}
			}
		}
		
		model.addAttribute("tournaments", tournaments);
		return "tournaments";
	}

	@RequestMapping(value = "/newTournament")
	public String newTrournament(Model model, @RequestParam(required = false) boolean error) {
		List<Game> games = gameRepository.findAll();
		model.addAttribute("games", games);
		model.addAttribute("error", error);
		return ("newTournament");
	}

	@RequestMapping(value = "/newTournament", method = RequestMethod.POST)
	public View addTournament(Model model, @RequestParam String name, @RequestParam String max,
			@RequestParam String gameName, @RequestParam String date, @RequestParam String time,
			@RequestParam String comment, @RequestParam String mode) {
		
		RedirectView rv;
		if (!tournamentRepository.findAllNames().contains(name)) {
			Game game = gameRepository.findByName(gameName);
			Tournament tournament = new Tournament(name, comment, Integer.parseInt(max), mode, date, game);
			User user = userRepository.findById(userComponent.getLoggedUser().getId());
			tournament.getAdmins().add(user);
			tournamentRepository.save(tournament);
			rv = new RedirectView("/tournaments");
		}else{
			rv = new RedirectView("/newTournament?error=true");
		}
		rv.setExposeModelAttributes(false);
		return rv;
	}

	@RequestMapping(value = "/tournament/{tournamentName}")
	public String tournament(Model model, @PathVariable String tournamentName,
			@RequestParam(required = false) boolean errorParticipant) {
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		model.addAttribute("tournament", thisTournament);
		model.addAttribute("errorParticipant", errorParticipant);
		if (userComponent.isLoggedUser()) {
			User user = userRepository.findById(userComponent.getLoggedUser().getId());
			if (thisTournament.getAdmins().contains(user) || user.getRoles().contains("ROLE_ADMIN")) {
				model.addAttribute("admin", true);
			}
			model.addAttribute("userIsInTournament", thisTournament.getParticipants().contains(user));
		}

		if (userComponent.isLoggedUser() && thisTournament.isStarted()) {
			Round round = thisTournament.getRounds().get(thisTournament.getRounds().size() - 1);
			User user = userRepository.findById(userComponent.getLoggedUser().getId());
			Team team;
			if (thisTournament.isSingleTournament()) {
				for (MatchUp m : round.getMatchUps()) {
					if (m.getPlayer1().equals(user) || m.getPlayer2().equals(user)) {
						if (!m.isFinished()) {
							model.addAttribute("userInMatch", true);
							model.addAttribute("userMatch", m);
							model.addAttribute("userRound", round);
							break;
						}
					}
				}
			} else if (user.getTeam() != null) {
				for (MatchUp m : round.getMatchUps()) {
					Team player1 = (Team) m.getPlayer1();
					Team player2 = (Team) m.getPlayer2();
					if (player1.getAdmins().contains(user) || player2.getAdmins().contains(user)) {
						if (!m.isFinished()) {
							model.addAttribute("userInMatch", true);
							model.addAttribute("userMatch", m);
							model.addAttribute("userRound", round);
							break;
						}
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
		if (thisTournament.isSingleTournament()) {
			if (!thisTournament.getParticipants().contains(user)) {
				user.getTournaments().add(thisTournament);
				userRepository.save(user);
				rv = new RedirectView("../tournament/" + tournamentName);
			} else {
				rv = new RedirectView("../tournament/" + tournamentName + "?errorParticipant=true");
			}
		} else {
			Team team = user.getTeam();
			if (team.getAdmins().contains(user)) {
				if (!thisTournament.getParticipants().contains(team)) {
					team.getTournaments().add(thisTournament);
					teamRepository.save(team);
					rv = new RedirectView("../tournament/" + tournamentName);
				} else {
					rv = new RedirectView("../tournament/" + tournamentName + "?errorParticipant=true");
				}
			} else {
				rv = new RedirectView("../tournaments?noAdmin=true");
			}
		}
		rv.setExposeModelAttributes(false);
		return rv;
	}

	@RequestMapping(value = "/tournament/{tournamentName}/startTournament", method = RequestMethod.POST)
	public View startTournament(Model model, @PathVariable String tournamentName) {
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		thisTournament.setStarted(true);
		for (Participant p : thisTournament.getParticipants()) {
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

	@RequestMapping(value = "/tournament/{tournamentName}/setMatchUp", method = RequestMethod.POST)
	public View setMatchUp(Model model, @PathVariable String tournamentName, @RequestParam Long idMatchUp,
			@RequestParam Long idRound, @RequestParam int result1, @RequestParam int result2) {

		Round round = roundRepository.findById(idRound);
		MatchUp matchup = matchUpRepository.findById(idMatchUp);
		Tournament tournament = tournamentRepository.findByName(tournamentName);
		if (result1 != result2) {
			matchup.setScore1(result1);
			matchup.setScore2(result2);
			matchup.setFinished(true);
			matchUpRepository.save(matchup);

		}
		RedirectView rv = new RedirectView("../../tournament/" + tournamentName);
		rv.setExposeModelAttributes(false);
		return rv;
	}

	@RequestMapping(value = "/tournament/{tournamentName}/confirmRound", method = RequestMethod.POST)
	public View confirmRound(Model model, @PathVariable String tournamentName, @RequestParam Long idRound) {
		Tournament tournament = tournamentRepository.findByName(tournamentName);
		Round round = roundRepository.findById(idRound);
		round.setClosedRound(true);
		for (MatchUp m : round.getMatchUps()) {
			tournament.getActualParticipants().remove(m.getLosser());
		}
		if (tournament.getActualParticipants().size() == 1) {
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
