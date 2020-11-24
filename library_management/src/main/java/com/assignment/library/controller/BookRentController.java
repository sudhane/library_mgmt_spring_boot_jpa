package com.assignment.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.library.model.Book;
import com.assignment.library.model.RentDetails;
import com.assignment.library.service.RentService;

@RestController
@RequestMapping("/api/v1")
public class BookRentController {

	private static final Logger log = LoggerFactory.getLogger(BookRentController.class);

	@Autowired
	private RentService rentService;

	@GetMapping("/rentedbooks")
	public List<RentDetails> getAllRentDetails() {
		log.info(" BookRentController:: Entered getAllRentDetails ");
		return rentService.getAllRentDetails();
	}
	
	@PostMapping("/rentedbooks/drawBook/{userId}/{bookId}")
	public Book drawBook(@PathVariable(value = "userId") Long userId, 
			@PathVariable(value = "bookId") Long bookId) {
		log.info(" BookRentController:: Entered drawBook ");
		return rentService.drawBook(userId, bookId);
	}
	
	@PutMapping("/rentedbooks/submitBook/{userId}/{bookId}")
	public Integer submitBook(@PathVariable(value = "userId") Long userId, 
			@PathVariable(value = "bookId") Long bookId) {
		log.info(" BookRentController:: Entered submitBook ");
		return rentService.submitBook(userId, bookId);
	}
}
