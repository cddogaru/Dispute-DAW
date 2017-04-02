package com.dispute.tournament;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dispute.user.UserComponent;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
	
	interface TournamentListView extends Tournament.BasicAtt {}
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	UserComponent userComponent;
	
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
	
}
