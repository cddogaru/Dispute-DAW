package com.dispute.team;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.dispute.game.Game;
import com.dispute.user.User;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"acronym"}))
public class Team {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(length = 3, nullable = false)
	private String acronym;
	
	@OneToMany(mappedBy="team")
	private List<User> users;
	
	@OneToMany
	private List<Game> games;
	
	protected Team() {}

	public Team(String name, String acronym) {
		this.name = name;
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	private int participants(){
		
		return users.size();
	}
	
	@Override
	public String toString() {
		return "Team [name=" + name + ", acronym=" + acronym + "]";
	}
}
