package com.dispute.tournament;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
	Tournament findByName(String name);

	List<Tournament> findByParticipants_Name(String name);

	List<Tournament> findByNameIgnoreCaseContaining(String name);

	List<Tournament> findByDescriptionIgnoreCaseContaining(String description);

	List<Tournament> findByNameIgnoreCaseContainingAndDescription(String name, String description);
	
	@Query("select name from Tournament")
	List<String> findAllNames();
}
