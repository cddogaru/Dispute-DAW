package com.dispute.tournament;


import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.Long;
import com.dispute.tournament.Round;
import java.util.List;


public interface RoundRepository extends JpaRepository<Round, Long>{
	Round findById(Long id);
}
