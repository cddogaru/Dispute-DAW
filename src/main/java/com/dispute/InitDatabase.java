package com.dispute;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dispute.game.Game;
import com.dispute.game.GameRepository;
import com.dispute.team.Team;
import com.dispute.team.TeamRepository;
import com.dispute.tournament.Tournament;
import com.dispute.tournament.TournamentRepository;
import com.dispute.user.*;

@Component
public class InitDatabase {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@PostConstruct
	public void init() {

 	 	Game hearthstone = new Game("Hearthstone", "Hearthstone");
 	 	Game counterStrike = new Game("Counter Strike - Global Offensive", "Counter");
 	 	Game Tekken = new Game("Tekken Tag Tournament 2", "Tekken");
 	 	gameRepository.save(hearthstone);
 	 	gameRepository.save(counterStrike);
 	 	gameRepository.save(Tekken);
		
		Team team1 = new Team("Real Madrid", "RMD", "");
		Team team2 = new Team("FC Barcelona", "FCB", "");
		teamRepository.save(team1);
		teamRepository.save(team2);
		for(int i=0; i<8; i++){
			Team team = new Team("Team " + i, "TM" + i, "");
			teamRepository.save(team);
		}
		
		team1.addGame(Tekken);
		team1.addGame(hearthstone);
		team1.addGame(counterStrike);
		

		User user1 = new User("Alex", "Alex", "alex@gmail.com", "1111", "ROLE_USER");
		User user2 = new User("Raul", "Raul", "raul@gmail.com", "2222", "ROLE_USER", "ROLE_ADMIN");
		User user3 = new User("Rafa", "Rafa", "rafa@gmail.com", "3333", "ROLE_USER");
		
		ArrayList<User> users = new ArrayList<User>();
		for(int i=0; i<10; i++){
			User user = new User("User" + i, "User" + i, "user" + i + "@gmail.com", "" + i, "ROLE_USER");
			users.add(user);
			if(i%2 == 0){
			user.setTeam(team1);
			} else{
			user.setTeam(team2);
			}
			userRepository.save(user);
		}
		
		user1.setTeam(team1);
		user2.setTeam(team2);
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	
		
		Tournament tournament1 = new Tournament("Torneo Hearthstone", "Hearthstone", 32, "1v1", "12-13-19 at 13:00");
		Tournament tournament2 = new Tournament("Counter Final CS | World's Cup", "Counter-Strike ", 32, "5v5", "12-13-19 at 13:00");
		Tournament tournament3 = new Tournament("Tekken X Street Fighter", "Tekken is a fighting video game ", 32, "5v5", "12-13-19 at 13:00");
		tournament1.getAdmins().add(user2);
		tournament2.getAdmins().add(user2);
		tournament3.getAdmins().add(user2);
		tournament1.setGame(hearthstone);
		tournament2.setGame(counterStrike);
		tournament3.setGame(Tekken);
		tournamentRepository.save(tournament1);
		tournamentRepository.save(tournament2);
		tournamentRepository.save(tournament3);
		
	 	team1.getTournaments().add(tournament2);
	 	team1.addAdmin(user2);
	 	teamRepository.save(team1);
	 	user1.getTournaments().add(tournament1);
	 	userRepository.save(user1);
	 	user3.getTournaments().add(tournament1);
	 	userRepository.save(user3);
	 	
	 	for(User u: users){
	 		u.getTournaments().add(tournament1);
	 		userRepository.save(u);
	 	}
	 	
 	 	
	}
	
}
