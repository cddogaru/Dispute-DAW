package com.dispute.tournament;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{
	Tournament findByName(String name);
	List<Tournament> findByParticipants_Name(String name);
	List<Tournament> findByNameIgnoreCaseContaining(String name);
}
