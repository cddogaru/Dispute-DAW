package com.dispute.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.dispute.participant.Participant;
import com.dispute.team.Team;
import com.dispute.tournament.Tournament;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class User extends Participant{


//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
	@Column(nullable = false)
	private String nickName;
	
//	@Column(nullable = false)
//	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@ManyToOne
	private Team team;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	private String avatar;
	//RRSS
	private String twitter;
	private String twitch;
	private String youtube;
	
	//Game Accounts
	private String steam;
	private String origin;
	private String battlenet;
	private String psn;
	private String xbox;
	

//	public Long getId() {
//		return id;
//	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	protected User() {}

	public User(String userName, String name, String email, String password, String... roles) {
		this.nickName = userName;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.setTournaments(new ArrayList<Tournament>());
		this.setName(name);
		this.email = email;
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.avatar = "Default";
	}

	public String getUserName() {
		return nickName;
	}

	public void setUserName(String userName) {
		this.nickName = userName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getTwitch() {
		return twitch;
	}

	public void setTwitch(String twitch) {
		this.twitch = twitch;
	}

	public String getYoutube() {
		return youtube;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getSteam() {
		return steam;
	}

	public void setSteam(String steam) {
		this.steam = steam;
	}

	public String getBattlenet() {
		return battlenet;
	}

	public void setBattlenet(String battlenet) {
		this.battlenet = battlenet;
	}

	public String getPsn() {
		return psn;
	}

	public void setPsn(String psn) {
		this.psn = psn;
	}

	public String getXbox() {
		return xbox;
	}

	public void setXbox(String xbox) {
		this.xbox = xbox;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User [userName=" + nickName + ", name=" + this.getName() + ", email=" + email + ", password=" + password
				+ ", team=" + team + "]";
	}
}
