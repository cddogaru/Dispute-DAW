package com.dispute.tournament;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.dispute.game.Game;
import com.dispute.participant.Participant;
import com.dispute.user.User;

@Entity
public class Tournament {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(length = 10000)
	private String description;
	private int maxPlayers;
	private String mode;
	private String date;
	@OneToOne
	private Game game;
	
	@ManyToMany(mappedBy = "tournaments")
	private List<Participant> participants;
	
	@OneToMany
	private List<User> admins;
	
	public Tournament(){}
	
	public Tournament(String name, String description, int maxPlayers, String mode, String date) {
		super();
		this.name = name;
		this.description = description;
		this.maxPlayers = maxPlayers;
		this.mode = mode;
		this.date = date;
		participants = new ArrayList<Participant>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> players) {
		this.participants = players;
	}
	
	public void addParticipant(Participant participant){
		 		if((!participants.contains(participant)) && ((participants.size()) <= maxPlayers)){
		 			participants.add(participant);
		 		}
		 	}
	public List<User> getAdmins() {
		return admins;
	}

	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}

	public int getNumOfParticipants(){
		 		return (this.participants.size());
		 	}
}
