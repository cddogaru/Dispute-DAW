package com.dispute.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	interface UserPublicView extends User.PublicDataUser{}
	interface UserPrivateView extends User.PrivateDataUser{}
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private UserComponent userComponent;
	
	@JsonView(UserPublicView.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<User> user(){
		return userRepository.findAll();
	}
	
	@JsonView(UserPrivateView.class)
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<User> modifyUser(@RequestBody User user){
		ResponseEntity<User> toRet;
		User modUser = userRepository.findByName(user.getName());
		
		if((user.anyNull()) && ((modUser.getRoles().contains("ROLE_ADMIN")) || ((userRepository.findByName(userComponent.getLoggedUser().getName())).getName().equals(user.getName())))){
			modUser.setAvatar(user.getAvatar());
			modUser.setEmail(user.getEmail());
			modUser.setName(user.getName());
			modUser.setNickName(user.getNickName());
			modUser.setEncryptedPassword(user.getPassword());
			modUser.setRoles(user.getRoles());
			modUser.setTeam(user.getTeam());
			
			modUser.setTwitter(user.getTwitter());
			modUser.setTwitch(user.getTwitch());
			modUser.setYoutube(user.getYoutube());
			modUser.setSteam(user.getSteam());
			modUser.setOrigin(user.getOrigin());
			modUser.setBattlenet(user.getBattlenet());
			modUser.setPsn(user.getPsn());
			modUser.setXbox(user.getXbox());
			
			userRepository.save(modUser);
			toRet = new ResponseEntity<>(modUser, HttpStatus.OK);
		}else{
			toRet = new ResponseEntity<>(modUser, HttpStatus.BAD_REQUEST);
		}
		return toRet;
	}
	
	@JsonView(UserPrivateView.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<User> newUser(@RequestBody User user){
		ResponseEntity<User> toRet;
		if(user.anyNull()){
			user.setAvatar("Default");
			userRepository.save(user);
			toRet = new ResponseEntity<>(user, HttpStatus.CREATED);
		}else{
			toRet = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return toRet;
	}
	
	@JsonView(UserPrivateView.class)
	@RequestMapping(value = "/loggedUser", method = RequestMethod.GET)
	public ResponseEntity<User> currentUser(){
		if (userComponent.getLoggedUser() != null) {
			return new ResponseEntity<>(userComponent.getLoggedUser(), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(UserPublicView.class)
	@RequestMapping(value = "/{user}", method = RequestMethod.GET)
	public ResponseEntity<User> user(@PathVariable String user){
		User toret = userRepository.findById(Long.parseLong(user));
		if(toret != null){
			return new ResponseEntity<>(toret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
