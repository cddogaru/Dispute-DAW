package com.dispute.tournament;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchUpRepository extends JpaRepository<MatchUp, Long>{
	MatchUp findById(Long id);
}
