package com.dispute.participant;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.dispute.tournament.Tournament;
import com.dispute.game.Game;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Participant{
	
	public interface BasicAtt extends Game.basicAtt{}
	
	@Id
	@JsonView(BasicAtt.class)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonView(BasicAtt.class)
	@Column(nullable = false)
	private String name;
	
	//@JsonView(BasicAtt.class)
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Tournament> tournaments; 
	
	@JsonView(BasicAtt.class)
	private String avatar;
	
	

	public List<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(List<Tournament> tournaments) {
		this.tournaments = tournaments;
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
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
