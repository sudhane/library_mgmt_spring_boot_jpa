package com.assignment.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.library.model.Shelf;

@Repository
public interface ShelfRepository  extends JpaRepository<Shelf, Long> {

	
	@Query(value = "SELECT s.quantity from Shelf s where s.bookId = :bookId")
	Integer findAvailableBookCountByBookId(@Param("bookId") Long bookid);
	
}
