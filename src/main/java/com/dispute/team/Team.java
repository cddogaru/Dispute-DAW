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
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"acronym"}))
public class Team extends Participant{


//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	@Column(nullable = false)
//	private String name;
	
	public interface basicAtt extends Participant.BasicAtt{}
	public interface adminAtt extends basicAtt{}
	
	@JsonView(basicAtt.class)
	@OneToOne
	private User creator;
	
	@JsonView(basicAtt.class)
	@Column(length = 3, nullable = false)
	private String acronym;
	
	@JsonView(basicAtt.class)
	@Column(length = 1000, nullable = true)
	private String description;
	
	@JsonView(basicAtt.class)
	@OneToMany(mappedBy="team")
	private List<User> users;
	
	@JsonView(basicAtt.class)
	@OneToMany
	private List<Game> games;
	
	@JsonView(basicAtt.class)
	@ManyToMany
	private List<User> admins;
	
	@JsonView(adminAtt.class)
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
		this.initLists();
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
	
	public void addUser(User user){
		this.users.add(user);
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public void addGame(Game game){
		this.games.add(game);
	}
	
	public void deleteGame(Game game){
		this.games.remove(game);
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
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void initLists(){
		users = new ArrayList<>();
		games = new ArrayList<>();
		admins = new ArrayList<>();
		requests = new ArrayList();
		this.setAvatar("DefaultTeam");
	}
	
	@Override
	public String toString() {
		return "Team [name=" + this.getName() + ", acronym=" + acronym + "]";
	}
}
