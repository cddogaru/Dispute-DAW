package com.dispute;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dispute.model.*;

@Component
public class initDatabase {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@PostConstruct
	public void init() {
		Team team1 = new Team("Real Madrid", "RMD");
		Team team2 = new Team("FC Barcelona", "FCB");
		
		teamRepository.save(team1);
		teamRepository.save(team2);
		
		User user1 = new User("Alex", "Alex", "alex@gmail.com", "1111");
		User user2 = new User("Raúl", "Raúl", "raul@gmail.com", "2222");
		User user3 = new User("Rafa", "Rafa", "rafa@gmail.com", "3333");
		
		user1.setTeam(team1);
		user2.setTeam(team2);
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		Tournament tournament1 = new Tournament("Torneo Hearthstone", "Hearthstone", 32, "1v1", "12-13-19 at 13:00");
		Tournament tournament2 = new Tournament("Counter Final CS | World's Cup", "Counter-Strike ", 32, "5v5", "12-13-19 at 13:00");
		Tournament tournament3 = new Tournament("Tekken X Street Fighter", "Tekken is a fighting video game ", 32, "5v5", "12-13-19 at 13:00");
//		tournament1.getPlayers().add(user1);
//		tournament1.getPlayers().add(user2);
//		tournament1.getPlayers().add(user3);
//		tournament2.getPlayers().add(user1);
//		tournament2.getPlayers().add(user2);
//		tournament2.getPlayers().add(user3);
//		tournament3.getPlayers().add(user1);
//		tournament3.getPlayers().add(user2);
//		tournament3.getPlayers().add(user3);
		tournamentRepository.save(tournament1);
		tournamentRepository.save(tournament2);
		tournamentRepository.save(tournament3);
	}
	
}
