package com.dispute.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	interface UserView extends User.BasicAtt{}
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private UserComponent userComponent;
	
	@JsonView(UserView.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User user(){
		return userComponent.getLoggedUser();
	}
}
