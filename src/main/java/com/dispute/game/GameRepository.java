package com.dispute.game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long>{
	Game findByName(String name);
	Game findById(Long id);
}
