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
import com.jayway.jsonpath.internal.function.numeric.Min;

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
 	 	Game LeagueOfLegends = new Game("League of Legends", "LOL");
 	 	Game CallOfDuty = new Game("Call of Duty: Black Ops 3", "COD");
 	 	Game Minecraft = new Game("Minecraft", "Minecraft");
 	 	Game Overwatch = new Game("Overwatch", "Overwatch");
 	 	Game Battlefield1 = new Game("Battlefield1", "BF1");
 	 	Game GTAV = new Game("Grand Theft Auto V", "GTAV");
 	 	Game NeedForSpeed = new Game("Need For Speed", "NFS");
 	 	Game Minesweeper = new Game("Minesweeper", "Minesweeper");
 	 	
 	 	gameRepository.save(hearthstone);
 	 	gameRepository.save(counterStrike);
 	 	gameRepository.save(Tekken);
		gameRepository.save(LeagueOfLegends);
		gameRepository.save(CallOfDuty);
		gameRepository.save(Minecraft);
		gameRepository.save(Overwatch);
		gameRepository.save(Battlefield1);
		gameRepository.save(GTAV);
		gameRepository.save(NeedForSpeed);
		gameRepository.save(Minesweeper);

		User user1 = new User("Alex", "Alex", "alex@gmail.com", "1111", "ROLE_USER");
		User user2 = new User("Raul", "Raul", "raul@gmail.com", "2222", "ROLE_USER", "ROLE_ADMIN");
		User user3 = new User("Rafa", "Rafa", "rafa@gmail.com", "3333", "ROLE_USER");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		Team team1 = new Team("Real Madrid", "RMD", "");
		Team team2 = new Team("FC Barcelona", "FCB", "");
		
		team1.setCreator(user2);
		team2.setCreator(user3);

		team1.addGame(Tekken);
		team1.addGame(hearthstone);
		team1.addGame(counterStrike);
		
		teamRepository.save(team1);
		teamRepository.save(team2);
		
		for(int i=0; i<8; i++){
			Team team = new Team("Team " + i, "TM" + i, "");
			team.setCreator(user1);
			teamRepository.save(team);
		}
		
		

		user1.setTeam(team1);
		user2.setTeam(team2);
		
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
		
		
		Tournament tournament1 = new Tournament("Hearthstone Tournament", "Hearthstone, originally known as Hearthstone: Heroes of Warcraft, is a free-to-play online collectible card video game developed and published by Blizzard Entertainment. ", 32, "Single", "12-13-19 at 13:00", hearthstone);
		Tournament tournament2 = new Tournament("Counter Final CS | World's Cup", "Counter-Strike (officially abbreviated as CS) is a series of multiplayer first-person shooter video games, in which teams of terrorists and counter-terrorists battle to, respectively, perpetrate an act of terror (bombing, hostage-taking) and prevent it (bomb defusal, hostage rescue).", 32, "Single", "12-13-19 at 13:00", counterStrike);
		Tournament tournament3 = new Tournament("Tekken X Street Fighter", "Tekken is a fighting video game franchise created, developed, and published by Namco (later Bandai Namco Entertainment). Beginning with the original Tekken in December 1994, the series has received several sequels as well as updates and spin-off titles.", 32, "Team", "12-13-19 at 13:00", Tekken);
		tournament1.getAdmins().add(user2);
		tournament2.getAdmins().add(user2);
		tournament3.getAdmins().add(user2);
		tournamentRepository.save(tournament1);
		tournamentRepository.save(tournament2);
		tournamentRepository.save(tournament3);
		
	 	team1.getTournaments().add(tournament3);
	 	team1.addAdmin(user2);
	 	teamRepository.save(team1);
	 	user1.getTournaments().add(tournament1);
	 	userRepository.save(user1);
	 	user3.getTournaments().add(tournament1);
	 	userRepository.save(user3);
	 	
	 	users.get(0).getTournaments().add(tournament2);
	 	users.get(1).getTournaments().add(tournament2);
	 	users.get(2).getTournaments().add(tournament2);
	 	users.get(3).getTournaments().add(tournament2);
	 	
	 	
	 	
	 	for(User u: users){
	 		u.getTournaments().add(tournament1);
	 		userRepository.save(u);
	 	}
	 	
 	 	
	}
	
}
