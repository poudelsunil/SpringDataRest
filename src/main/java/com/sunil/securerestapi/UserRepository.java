package com.sunil.securerestapi;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasAnyRole('ROLE_USER')")
public interface UserRepository extends JpaRepository<User, Long>{
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Collection<User> findByUsernameLike(@Param("username") String username);
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Optional<User> findById(@Param("id") long id);
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public java.util.List<User> findAll();
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<Show> findUserShowById(@Param("id") long id);
	
	@SuppressWarnings("unchecked")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	User save(User s);

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	void delete(Long aLong);
	
}
