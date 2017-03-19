package com.dispute.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.dispute.game.Game;
import com.dispute.participant.Participant;
import com.dispute.tournament.Tournament;
import com.dispute.user.User;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"acronym"}))
public class Team extends Participant{


//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	@Column(nullable = false)
//	private String name;
	
	@Column(length = 3, nullable = false)
	private String acronym;
	
	@Column(length = 1000, nullable = true)
	private String description;
	
	@OneToMany(mappedBy="team")
	private List<User> users;
	
	@OneToMany
	private List<Game> games;
	
	@ManyToMany
	private List<User> admins;
	
	@ElementCollection
	@CollectionTable(name ="requests")
	private List<Long> requests;
	
	protected Team() {}

	public Team(String name, String acronym, String description) {
		this.setName(name);
		this.setTournaments(new ArrayList<Tournament>());
//		this.name = name;
		this.acronym = acronym;
		this.description = description;
		users = new ArrayList<>();
		games = new ArrayList<>();
		admins = new ArrayList<>();
		requests = new ArrayList();
		this.setAvatar("DefaultTeam");
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public List<User> getAdmins() {
		return admins;
	}

	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}
	
	public void addAdmin(User user){
		this.admins.add(user);
	}
	
	public boolean isAdmin(User user){
		return admins.contains(user);
	}
	public List<Long> getRequests() {
		return requests;
	}

	public void setRequests(List<Long> requests) {
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "Team [name=" + this.getName() + ", acronym=" + acronym + "]";
	}
}
