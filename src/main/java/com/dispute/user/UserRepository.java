package com.dispute.user;

import javax.transaction.Transactional;
import com.dispute.participant.ParticipantRepository;

@Transactional
public interface UserRepository extends ParticipantRepository<User> {
	User findBynickName(String nickName);
	User findByName(String name);
	User findById(Long id);
}