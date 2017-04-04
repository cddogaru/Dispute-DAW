package com.dispute.team;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dispute.game.GameRepository;
import com.dispute.user.User;
import com.dispute.user.UserComponent;
import com.dispute.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/api/teams")
public class TeamRestController {

	interface basicAtt extends Team.basicAtt{}
	interface adminAtt extends Team.adminAtt{}

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private GameRepository gameRepository;
	
	@JsonView(basicAtt.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	private List<Team> teams(){
		return teamRepository.findAll();
	}
	
	@JsonView(basicAtt.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	private ResponseEntity<Team> newTeam(@RequestBody Team team){
		ResponseEntity<Team> toRet;
		User currentUser = userRepository.findByName(userComponent.getLoggedUser().getName());
		if((!(teamRepository.findAllAcronyms().contains(team.getAcronym()))) && (team.getAcronym().length() <= 3)){
			team.initLists();
			team.addAdmin(currentUser);
			team.setCreator(currentUser);
			
			teamRepository.save(team);
			
			toRet = new ResponseEntity<>(team, HttpStatus.OK);
		}else{
			toRet = new ResponseEntity<>(team, HttpStatus.BAD_REQUEST);
		}

		return toRet;
	}

	@JsonView(basicAtt.class)
	@RequestMapping(value = "/{team}", method = RequestMethod.GET)
	public ResponseEntity<Team> team(@PathVariable String team) {
		Team toret = teamRepository.findByName(team);
		if (toret != null) {
			return new ResponseEntity<>(toret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}



	// Requests
	@JsonView(adminAtt.class)
	@RequestMapping(value = "/{team}/admin", method = RequestMethod.GET)
	private ResponseEntity<List<Long>> getRequestsTeam(@PathVariable String team){
		ResponseEntity<List<Long>> toRet;
		Team currentTeam = teamRepository.findByName(team);
		User currentUser = userRepository.findByName(userComponent.getLoggedUser().getName());
		
		if((currentTeam.isAdmin(currentUser)) || (currentUser.getRoles().contains("ROLE_ADMIN"))){
			toRet = new ResponseEntity<>(currentTeam.getRequests(), HttpStatus.OK);
		}else{
			toRet = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return toRet;
	}
	
	// Accept Request if is in the Requests or Make admin else
	@JsonView(basicAtt.class)
	@RequestMapping(value = "/{team}/admin", method = RequestMethod.PUT)
	private ResponseEntity<Team> adminTeamPut(@PathVariable String team, @RequestBody String sid){
		Long id = Long.parseLong(sid);
		ResponseEntity<Team> toRet;
		Team currentTeam = teamRepository.findByName(team);
		User currentUser = userRepository.findByName(userComponent.getLoggedUser().getName());
		List<Long> requests = currentTeam.getRequests();
		List<User> members = currentTeam.getUsers();
		User toPromote = userRepository.findById(id);
		
		if ((currentTeam.isAdmin(currentUser)) || (currentUser.getRoles().contains("ROLE_ADMIN"))) {
			if (requests.contains(id) || (members.contains(toPromote))) {
				if (requests.contains(id)) {
					currentTeam.addUser(toPromote);
					requests.remove(id);
					toPromote.setTeam(currentTeam);
					
					userRepository.save(toPromote);
					teamRepository.save(currentTeam);

					toRet = new ResponseEntity<>(currentTeam, HttpStatus.OK);
				} else {
					currentTeam.addAdmin(toPromote);
					teamRepository.save(currentTeam);

					toRet = new ResponseEntity<>(currentTeam, HttpStatus.OK);
				}
			}else{
				toRet = new ResponseEntity<>(currentTeam, HttpStatus.BAD_REQUEST);
			}
		}else{
			toRet = new ResponseEntity<>(currentTeam, HttpStatus.BAD_REQUEST);
		}
		
		return toRet;
	}
	
	
	// kick user, deny entry or leave team
	@JsonView(basicAtt.class)
	@RequestMapping(value = "/{team}/admin", method = RequestMethod.DELETE)
	private ResponseEntity<Team> adminTeamDelete(@PathVariable String team, @RequestBody String sid){
		Long id = Long.parseLong(sid);
		ResponseEntity<Team> toRet;
		Team currentTeam = teamRepository.findByName(team);
		User currentUser = userRepository.findByName(userComponent.getLoggedUser().getName());
		List<Long> requests = currentTeam.getRequests();
		List<User> members = currentTeam.getUsers();
		User toRemove = userRepository.findById(id);
		
		if ((currentTeam.isAdmin(currentUser)) || (currentUser.getRoles().contains("ROLE_ADMIN")) || (currentUser.getId() == id)) {
			if (requests.contains(id) || (members.contains(toRemove))) {
				if (requests.contains(id)) {
					requests.remove(id);
					toRemove.setTeam(null);
					
					userRepository.save(toRemove);
					teamRepository.save(currentTeam);

					toRet = new ResponseEntity<>(currentTeam, HttpStatus.OK);
				} else {
					List<User> admins = currentTeam.getAdmins();
					if(admins.contains(toRemove)){
						admins.remove(toRemove);
					}
					if(members.contains(toRemove)){
						members.remove(toRemove);
					}
					currentTeam.setAdmins(admins);
					toRemove.setTeam(null);		
					
					userRepository.save(toRemove);
					teamRepository.save(currentTeam);

					toRet = new ResponseEntity<>(currentTeam, HttpStatus.OK);
				}
			}else{
				toRet = new ResponseEntity<>(currentTeam, HttpStatus.BAD_REQUEST);
			}
		}else{
			toRet = new ResponseEntity<>(currentTeam, HttpStatus.BAD_REQUEST);
		}
		
		return toRet;
	}
	

}
