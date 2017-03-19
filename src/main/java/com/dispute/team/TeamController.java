package com.dispute.team;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

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

import com.dispute.user.User;
import com.dispute.user.UserComponent;
import com.dispute.user.UserRepository;

@Controller
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping(value = "/teams")
	public String teams(Model model){
		
		model.addAttribute("teams", teamRepository.findAll());
		return("teams");
	}
	
	@RequestMapping(value = "/newTeam")
	public String newTeam(Model model){
		return("newTeam");
	}
	
	@RequestMapping(value = "/newTeam", method = RequestMethod.POST)
	public View addTeam(Model model, @RequestParam String name, @RequestParam String acronym, @RequestParam String games, @RequestParam String description){
		Team team = new Team(name, acronym, description);
		team.addAdmin(userComponent.getLoggedUser());
		teamRepository.save(team);
		
		RedirectView rv = new RedirectView("teams.html");
 		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping(value="/team/{teamName}")
	public String teamRequest(Model model, @PathVariable String teamName, @RequestParam(required = false) boolean error, @RequestParam(required = false) boolean request){
		Team team = teamRepository.findByName(teamName);
		model.addAttribute("actualTeam", team);
		model.addAttribute("error", error);
		model.addAttribute("request", request);
		if(userComponent.isLoggedUser()){
			User user = userRepository.findByName(userComponent.getLoggedUser().getName());
			model.addAttribute("admin", team.isAdmin(user));
			List<User> requests = new ArrayList<>();
			for(Long id: team.getRequests()){
				requests.add(userRepository.findById(id));
			}
			
			
			if(user.getTeam() == null && !team.getRequests().contains(user.getId())){
				model.addAttribute("doRequest", true);
			} else {
				if(team.getUsers().contains(user)){
					model.addAttribute("leaveTeam", true);
				}
			}
			model.addAttribute("requests", requests);
		}
		return "team";
	}
	
	@RequestMapping(value = "/team/kickUser", method = RequestMethod.POST)
	public View kickUser(Model model, @RequestParam Long teamId, @RequestParam Long userId){

		User user = userRepository.findById(userId);
		Team team = teamRepository.findById(teamId);
		
		RedirectView rv;
		if(user.getTeam()!=null && user.getTeam().equals(team)){
			if(team.isAdmin(user)){
				team.getAdmins().remove(user);
			}
			user.setTeam(null);
			rv = new RedirectView("../team/" + team.getName());
		} else {
			rv = new RedirectView("../team/" + team.getName() + "?error=true");
		}
		userRepository.save(user);
 		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping(value = "/team/addAdmin", method = RequestMethod.POST)
	public View addAdmin(Model model, @RequestParam Long teamId, @RequestParam Long userId){

		User user = userRepository.findById(userId);
		Team team = teamRepository.findById(teamId);
		
		RedirectView rv;
		if(!team.isAdmin(user) && user.getTeam().equals(team)){
			team.addAdmin(user);
			rv = new RedirectView("../team/" + team.getName());
		} else {
			rv = new RedirectView("../team/" + team.getName() + "?error=true");
		}
		teamRepository.save(team);
 		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping(value="/team/{teamName}/newRequest", method = RequestMethod.POST)
	public View addAdmin(Model model, @PathVariable String teamName, @RequestParam Long userId){

		Team team = teamRepository.findByName(teamName);
		
		RedirectView rv;
		if(!team.getRequests().contains(userId)){
			team.getRequests().add(userId);
			rv = new RedirectView("../../team/" + teamName + "?request=true");
		} else {
			rv = new RedirectView("../../team/" + teamName + "?error=true");
		}
		teamRepository.save(team);
 		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping(value="/team/{teamName}/acceptRequest", method = RequestMethod.POST)
	public View addUser(Model model, @PathVariable String teamName, @RequestParam Long userId, @RequestParam boolean accept){

		Team team = teamRepository.findByName(teamName);
		User user = userRepository.findById(userId);
		RedirectView rv;
		if (accept) {
			if (team.getRequests().contains(userId) && user.getTeam() == null) {
				team.getRequests().remove(userId);
				user.setTeam(team);
				userRepository.save(user);
				teamRepository.save(team);
				rv = new RedirectView("../../team/" + teamName);
			} else {
				team.getRequests().remove(userId);
				rv = new RedirectView("../../team/" + teamName + "?error=true");
			}
		} else {
			if (team.getRequests().contains(userId)) {
				team.getRequests().remove(userId);
				teamRepository.save(team);
				rv = new RedirectView("../../team/" + teamName);
			} else {
				team.getRequests().remove(userId);
				rv = new RedirectView("../../team/" + teamName + "?error=true");
			}
		}
		rv.setExposeModelAttributes(false);
		return rv;
	}
}
