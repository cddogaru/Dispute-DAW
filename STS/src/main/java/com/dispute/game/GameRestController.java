package com.dispute.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/api/games")
public class GameRestController {
	interface basicAtt extends Game.basicAtt{};
	@Autowired
	private GameRepository gameRepository;
	
	@JsonView(basicAtt.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	private List<Game> teams(){
		return gameRepository.findAll();
	}
}
