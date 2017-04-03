package com.dispute.user;

import java.util.ArrayList;
import java.util.Arrays;
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
		User toret = userRepository.findByName(user);
		if(toret != null){
			return new ResponseEntity<>(toret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
