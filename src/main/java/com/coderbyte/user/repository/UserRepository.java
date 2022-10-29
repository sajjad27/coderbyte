package com.coderbyte.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coderbyte.auth.model.entity.User;
import com.coderbyte.user.model.dao.response.UserSummaryResponse;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsernameIgnoreCase(String username);
	
	@Query(value = "SELECT "
			+ " new com.coderbyte.user.model.dao.response.UserSummaryResponse(usr.id, usr.name, usr.email, usr.gender, usr.isActivated) "
			+ " FROM User usr "
			+ " ORDER BY usr.id asc")
	public List<UserSummaryResponse> findAllAsSummary();
}