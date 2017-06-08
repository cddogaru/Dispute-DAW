package com.dispute.tournament;

import com.dispute.user.User;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.dispute.participant.Participant;
import com.dispute.tournament.Tournament.BasicAtt;;

@Entity
public class MatchUp {
	
	public interface BasicAtt {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(BasicAtt.class)
	private Long id;
	
	@JsonView(BasicAtt.class)
	@ManyToOne
	private Participant player1, player2;
	@JsonView(BasicAtt.class)
	private int score1 = 0;
	@JsonView(BasicAtt.class)
	private int score2 = 0;
	@JsonView(BasicAtt.class)
	private boolean finished;
	public MatchUp(){}
	
	public MatchUp(Participant player1, Participant player2){
		this.player1 = player1;
		this.player2 = player2;
	}

	public Participant getPlayer1() {
		return player1;
	}

	public void setPlayer1(Participant player1) {
		this.player1 = player1;
	}

	public Participant getPlayer2() {
		return player2;
	}

	public void setPlayer2(Participant player2) {
		this.player2 = player2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Participant getWinner(){
		if(score1 > score2){
			return player1;
		} else{
			return player2;
		}
	}
	
	public Participant getLosser(){
		if(score1 > score2){
			return player2;
		} else{
			return player1;
		}
	}
}
