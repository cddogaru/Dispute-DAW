package com.dispute.tournament;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.dispute.game.GameRepository;
import com.dispute.participant.Participant;
import com.dispute.team.TeamRepository;
import com.dispute.user.User;
import com.dispute.user.UserComponent;
import com.dispute.user.UserRepository;

@Service
public class TournamentService {
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
	
	public boolean startTournament(String tournamentName){
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		User user = userRepository.findById(userComponent.getLoggedUser().getId());
		boolean isAdmin = thisTournament.getAdmins().contains(user) || user.getRoles().contains("ROLE_ADMIN");
		if (isAdmin) {
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
		}
		return isAdmin;
	}
	
	public void confirmRound(String tournamentName, Long idRound){
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
	}
}
