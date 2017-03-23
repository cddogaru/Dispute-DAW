package com.dispute.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import com.dispute.participant.ParticipantRepository;

@Transactional
public interface UserRepository extends ParticipantRepository<User> {
	User findBynickName(String nickName);
	User findByName(String name);
	User findById(Long id);
	
	@Query("select name from User")
	List<String> findAllNames();
}