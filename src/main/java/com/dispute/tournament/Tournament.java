package com.dispute.tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

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

	@ManyToMany
	private List<User> admins;
	
	@ManyToMany
	private List<Participant> actualParticipants;
	
	@OneToMany
	private List<Round> rounds;
	
	private int roundNumber = 0;
	
	private boolean started;
	
	private boolean finished;
	
	public Tournament() {
	}

	public Tournament(String name, String description, int maxPlayers, String mode, String date) {
		super();
		this.name = name;
		this.description = description;
		this.maxPlayers = maxPlayers;
		this.mode = mode;
		this.date = date;
		participants = new ArrayList<Participant>();
		actualParticipants = new ArrayList<Participant>();
		admins = new ArrayList<User>();
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

	public void addParticipant(Participant participant) {
		if ((!participants.contains(participant)) && ((participants.size()) <= maxPlayers)) {
			participants.add(participant);
		}
	}

	public List<User> getAdmins() {
		return admins;
	}

	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}

	public List<Participant> getActualParticipants() {
		return actualParticipants;
	}

	public void setActualParticipants(List<Participant> actualParticipants) {
		this.actualParticipants = actualParticipants;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public int getNumOfParticipants() {
		return (this.participants.size());
	}

	public void randomizeParticipants(){
		long seed = System.nanoTime();
		Collections.shuffle(actualParticipants, new Random(seed));
	}
	
	public boolean isStarted() {
		return started;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finishedTournament) {
		this.finished = finishedTournament;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public ArrayList<MatchUp> generateMatchUps() {
		randomizeParticipants();
		ArrayList<MatchUp> matchups = new ArrayList<MatchUp>();
		int i = 0;
		while (i < actualParticipants.size()) {
			if ((i + 1) < actualParticipants.size()) {
				MatchUp m1 = new MatchUp(actualParticipants.get(i), actualParticipants.get(i + 1));
				matchups.add(m1);
			}
			i=i+2;
		}
		return matchups;
	}
	
	public Round newRound(ArrayList<MatchUp> matchups){
		roundNumber++;
		Round round = new Round("Round " + roundNumber);
		round.setMatchUps(matchups);
		if(actualParticipants.size() % 2 != 0){
			round.setOddRound(true);
			round.setLastParticipant(actualParticipants.get(actualParticipants.size()-1));
		}
		rounds.add(round);
		return round;
	}
}
