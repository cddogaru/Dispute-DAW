package com.dispute.tournament;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dispute.game.Game;
import com.dispute.game.GameRepository;
import com.dispute.participant.Participant;
import com.dispute.participant.ParticipantRepository;
import com.dispute.security.UserRepositoryAuthenticationProvider;
import com.dispute.team.TeamRepository;
import com.dispute.user.User;
import com.dispute.user.UserComponent;
import com.dispute.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
	
	interface TournamentListView extends Tournament.BasicAtt, User.PublicDataUser {}
	interface TournamentRoundsView extends Tournament.BasicAtt, Round.BasicAtt, MatchUp.BasicAtt {}
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	UserComponent userComponent;
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	TournamentService tournamentService;
	
	@Autowired
	MatchUpRepository matchUpRepository;
	
	@Autowired
	RoundRepository roundRepository;
	
	@Autowired
	GameRepository gameRepository;
	

	@JsonView(TournamentListView.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Tournament> tournaments(){
		return tournamentRepository.findAll();
	}
	
	@JsonView(TournamentListView.class)
	@RequestMapping(value = "/{tournamentName}", method = RequestMethod.GET)
	public ResponseEntity<Tournament> tournament(@PathVariable String tournamentName){
		Tournament tournament = tournamentRepository.findByName(tournamentName);
		if(tournament != null){
			return new ResponseEntity<>(tournament, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Tournament newTournament(@RequestBody Tournament tournament){
		Game game = tournament.getGame();
		if(game != null){
			tournament.setGame(gameRepository.findByName(game.getName()));
		}
		tournamentRepository.save(tournament);
		return tournament;
	}
	
	@JsonView(TournamentRoundsView.class)
	@RequestMapping(value = "/{tournamentName}", method = RequestMethod.PUT)
	public ResponseEntity<Tournament> editTournament(@RequestBody Tournament tournament, @PathVariable String tournamentName){
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		User loggedUser = userRepository.findById(userComponent.getLoggedUser().getId());
		if(thisTournament.getAdmins().contains(loggedUser) || loggedUser.getRoles().contains("ROLE_ADMIN")){
			if(!thisTournament.isStarted() && tournament.isStarted()){
				tournamentService.startTournament(tournamentName);
			}
			return new ResponseEntity<>(thisTournament, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(TournamentRoundsView.class)
	@RequestMapping(value = "/{tournamentName}/rounds", method = RequestMethod.GET)
	public ResponseEntity<List<Round>> roundsTournament(@PathVariable String tournamentName){
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		return new ResponseEntity<>(thisTournament.getRounds(), HttpStatus.OK);
	}
	
	@JsonView(TournamentRoundsView.class)
	@RequestMapping(value = "/{tournamentName}/rounds/{idRound}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Round> round(@PathVariable String tournamentName, @PathVariable Long idRound){
		Round round = roundRepository.findById(idRound);
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		if(thisTournament.getRounds().contains(round)){
			return new ResponseEntity<>(round, HttpStatus.OK);
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(TournamentRoundsView.class)
	@RequestMapping(value = "/{tournamentName}/rounds/{idRound}/matchUps", method = RequestMethod.GET)
	public ResponseEntity<List<MatchUp>> matchUpsTournament(@PathVariable String tournamentName,  @PathVariable Long idRound){
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		Round round = roundRepository.findById(idRound);
		if(thisTournament.getRounds().contains(round)){
			return new ResponseEntity<>(round.getMatchUps(), HttpStatus.OK);
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(TournamentRoundsView.class)
	@RequestMapping(value = "/{tournamentName}/rounds/{idRound}/matchUps/{idMatchUp}", method = RequestMethod.GET)
	public ResponseEntity<MatchUp> matchUp(@PathVariable String tournamentName,  @PathVariable Long idRound,  @PathVariable Long idMatchUp){
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		Round round = roundRepository.findById(idRound);
		MatchUp matchUp = matchUpRepository.findById(idMatchUp);
		if(thisTournament.getRounds().contains(round) && round.getMatchUps().contains(matchUp)){
			return new ResponseEntity<>(matchUp, HttpStatus.OK);
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(TournamentRoundsView.class)
	@RequestMapping(value = "/{tournamentName}/rounds/{idRound}/matchUps/{idMatchUp}", method = RequestMethod.PUT)
	public ResponseEntity<MatchUp> setMatchUp(@RequestBody MatchUp matchUp, @PathVariable String tournamentName,  @PathVariable Long idRound,  @PathVariable Long idMatchUp){
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		Round round = roundRepository.findById(idRound);
		MatchUp thisMatchUp = matchUpRepository.findById(idMatchUp);
		if(thisTournament.getRounds().contains(round) && round.getMatchUps().contains(thisMatchUp)){
			thisMatchUp.setScore1(matchUp.getScore1());
			thisMatchUp.setScore2(matchUp.getScore2());
			thisMatchUp.setFinished(matchUp.isFinished());
			
			matchUpRepository.save(thisMatchUp);
			return new ResponseEntity<>(thisMatchUp, HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@JsonView(TournamentRoundsView.class)
	@RequestMapping(value = "/{tournamentName}/rounds/{idRound}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Round> finishRound(@RequestBody Round round, @PathVariable String tournamentName, @PathVariable Long idRound){
		Round thisRound = roundRepository.findById(idRound);
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		if(thisTournament.getRounds().contains(thisRound)){
			if(round.isClosedRound() && !thisRound.isClosedRound()){
				tournamentService.confirmRound(tournamentName, idRound);
			}
			return new ResponseEntity<>(thisRound, HttpStatus.OK);
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(TournamentListView.class)
	@RequestMapping(value = "/{tournamentName}/participants", method = RequestMethod.GET)
	public ResponseEntity<List<Participant>> participants(@PathVariable String tournamentName){
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		return new ResponseEntity<>(thisTournament.getParticipants(), HttpStatus.OK);
	}
	
	/**
	@JsonView(TournamentListView.class)
	@RequestMapping(value = "/{tournamentName}/participants", method = RequestMethod.PUT)
	public ResponseEntity<List<Participant>> addParticipant(@RequestBody Participant participant, @PathVariable String tournamentName){
		Tournament thisTournament = tournamentRepository.findByName(tournamentName);
		User loggedUser = userRepository.findById(userComponent.getLoggedUser().getId());
		if(participant){
			if(loggedUser.equals((User) thisParticipant)){
				thisParticipant.getTournaments().add(thisTournament);
				participantRepository.save(thisParticipant);
			}
		} else {
			System.out.println("Team");
		}
		return new ResponseEntity<>(thisTournament.getParticipants(), HttpStatus.OK);
	}
	**/

}
