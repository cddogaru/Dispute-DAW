package com.dispute.team;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import com.dispute.participant.ParticipantRepository;

import java.util.List;

@Transactional
public interface TeamRepository extends ParticipantRepository<Team> {
	Team findByName(String name);
	Team findById(Long id);
	Team findByAcronym(String acronym);
	
	@Query("select name from Team")
	List<String> findAllNames();
	
	@Query("select acronym from Team")
	List<String> findAllAcronyms();
}
