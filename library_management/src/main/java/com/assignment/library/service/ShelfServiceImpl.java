package com.assignment.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.library.repository.ShelfRepository;

@Service("shelfService")
public class ShelfServiceImpl implements ShelfService {
	
	private static final Logger log = LoggerFactory.getLogger(ShelfServiceImpl.class);
	
	@Autowired
	ShelfRepository shelfRepository;
	
	public Integer getAvailableBooksByBookId(Long bookId) {
		log.info(" Entered getAvailableBooksByBookId ");
		Integer availableBookCount = null;
		
		if(bookId != null) {
			availableBookCount = shelfRepository.findAvailableBookCountByBookId(bookId);
		}
		
		return availableBookCount;
	}

}
