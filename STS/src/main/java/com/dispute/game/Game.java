package com.dispute.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dispute.team.Team.basicAtt;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Game {
	
	public interface basicAtt{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@JsonView(basicAtt.class)
	String name;
	
	@JsonView(basicAtt.class)
	String img;
	
	protected Game(){}
	
	public Game(String name, String img){
		this.name = name;
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}