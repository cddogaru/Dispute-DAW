package com.dispute.tournament;

import com.dispute.user.User;
import com.dispute.participant.Participant;;

public class MatchUp {
	private Participant player1, player2;
	private int[] score = new int[2];
	
	public MatchUp(Participant player1, Participant player2){
		this.player1 = player1;
		this.player2 = player2;
		score[0] = 0;
		score[1] = 0;
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

	public int[] getScore() {
		return score;
	}

	public void setScore(int[] score) {
		this.score = score;
	}
	
	public void setScoreOnePlayer(int score, Participant player){
		if(player.equals(this.player1)){
			this.score[0] = score;
		}
		if(player.equals(this.player2)){
			this.score[1] = score;
		}	
	}
}
