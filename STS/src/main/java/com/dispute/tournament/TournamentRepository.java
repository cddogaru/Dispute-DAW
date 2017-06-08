package com.dispute.tournament;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
	Tournament findByName(String name);
	Tournament findById(Long id);
	List<Tournament> findByParticipants_Name(String name);

	List<Tournament> findByNameIgnoreCaseContaining(String name);
	
	@Query("SELECT name FROM Tournament")
	List<String> findAllNames();
}
