package com.assignment.library.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.library.model.RentDetails;

@Repository
public interface RentDetailsRepository extends JpaRepository<RentDetails, Long>{
	
	@Query(value = "SELECT COUNT(rd.id) from RentDetails rd where rd.bookId = :bookId and rd.status = 0  ")
	Integer findRentableStatusByBookId(@Param("bookId") Long bookId);

	
	@Query(value = " SELECT COUNT(rdu.id) as issuedbooks from RentDetails rdu where rdu.userId = :userId and rdu.status = 0  ")
	Integer findRentableStatusOfUser(@Param("userId") Long userId);

	@Modifying
	@Query(value = "update RentDetails rd set rd.status = 1 , rd.actualReturnDate = :currentDate where rd.bookId = :bookId and rd.userId = :userId ")
	Integer updateBookStatusAndActualReturnDate(@Param("userId") Long userId, @Param("bookId") Long bookId, @Param("currentDate") LocalDate currentDate);
	
	@Query(value = "SELECT rd from RentDetails rd where rd.bookId = :bookId and rd.userId = :userId and rd.status = 0") 
	RentDetails findRentDetailsByUserBookStatus(@Param("userId") Long userId, @Param("bookId") Long bookId);
}

