package com.dispute.tournament;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{
	Tournament findByName(String name);
}
