package com.assignment.library.service;

import java.util.List;

import com.assignment.library.model.Book;
import com.assignment.library.model.RentDetails;

public interface RentService {
	
	Book drawBook(Long userId, Long bookId);
	
	Integer submitBook(Long userId, Long bookId);
	
	List<RentDetails> getAllRentDetails();

}
