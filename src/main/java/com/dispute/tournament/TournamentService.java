package com.dispute.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dispute.game.GameRepository;
import com.dispute.team.TeamRepository;
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
	
	
	
}
