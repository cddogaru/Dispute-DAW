package com.dispute.team;

import javax.transaction.Transactional;
import com.dispute.participant.ParticipantRepository;

@Transactional
public interface TeamRepository extends ParticipantRepository<Team> {
	Team findByName(String name);
	Team findById(Long id);
}
