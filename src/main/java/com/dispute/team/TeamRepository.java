package com.dispute.team;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dispute.participant.ParticipantRepository;

@Transactional
public interface TeamRepository extends ParticipantRepository<Team> {
	Team findByName(String name);
}
