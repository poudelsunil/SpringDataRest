package com.sunil.securerestapi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import com.fasterxml.jackson.annotation.JsonIgnore;

@PreAuthorize("hasAnyRole('ROLE_USER')")
public interface ShowRepository extends JpaRepository<Show, Long> {
	//List<Show> findByUserUsername(String username);
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	List<Show> findByNameLike(@Param("showname") String showname);
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@JsonIgnore
	Optional<Show> findById(@Param("id") long id);
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@Query("select s from Show s where s.totalSeat > s.bookedSeat AND s.openForBook = true ")
	List<Show> openForBooking();
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	List<Show> findAll();
	
	
	//Show findByIdOpenForBook(@Param("id") long _id);
	
}