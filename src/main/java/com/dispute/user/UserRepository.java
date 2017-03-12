package com.dispute.user;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dispute.participant.ParticipantRepository;

@Transactional
public interface UserRepository extends ParticipantRepository<User> {
	User findBynickName(String nickName);
	User findByName(String name);
}