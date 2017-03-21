package com.dispute.tournament;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.dispute.participant.Participant;

@Entity
public class Round {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany
	private List<MatchUp> matchUps;
	
	private boolean oddRound;
	
	@ManyToOne
	private Participant lastParticipant;
	
	private boolean firstRound;
	
	private boolean closedRound = false;
	
	public Round(){}
	
	public Round(String name){
		this.name = name;
		this.matchUps = new ArrayList<>();
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

	public List<MatchUp> getMatchUps() {
		return matchUps;
	}

	public void setMatchUps(List<MatchUp> matchUps) {
		this.matchUps = matchUps;
	}
	
	public boolean isOddRound(){
		return oddRound;
	}
	


	public void setOddRound(boolean oddRound) {
		this.oddRound = oddRound;
	}

	public Participant getLastParticipant() {
		return lastParticipant;
	}

	public void setLastParticipant(Participant lastParticipant) {
		this.lastParticipant = lastParticipant;
	}
	
	public boolean isFinishedRound(){
		boolean finished = true;
		for(MatchUp m: this.matchUps){
			if(!m.isFinished()){
				finished = false;
			}
		}
		return finished;
	}
	
	public Long getRoundId(){
		return id;
	}

	public boolean isFirstRound() {
		return firstRound;
	}

	public void setFirstRound(boolean firstRound) {
		this.firstRound = firstRound;
	}

	public boolean isClosedRound() {
		return closedRound;
	}

	public void setClosedRound(boolean closedRound) {
		this.closedRound = closedRound;
	}
}
