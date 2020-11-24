package com.assignment.library.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.library.model.Book;
import com.assignment.library.model.RentDetails;
import com.assignment.library.model.User;
import com.assignment.library.repository.RentDetailsRepository;

@Service("rentService")
public class RentServiceImpl implements RentService {
	
	private static final Logger log = LoggerFactory.getLogger(RentServiceImpl.class);
	
	@Autowired
	RentDetailsRepository rentDetailsRepository;
	
	@Autowired
	ShelfService shelfService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	public List<RentDetails> getAllRentDetails() {
		return rentDetailsRepository.findAll();
	}

	@Transactional
	public Book drawBook(Long userId, Long bookId) {
		log.info(" Enterd drawBook ");
		
		Book rentedBook = null;
		
		if(isUserAllowToRent(userId)) {
			Integer availableBookCount = shelfService.getAvailableBooksByBookId(bookId);		
			if(availableBookCount > 0) {
				log.info(" Shelf do have books !!! ");
				synchronized(this) {
					if(isEnoughBooksAvailableOnShelf(availableBookCount, userId, bookId)) {			
						RentDetails rentDetails = rentDetailsRepository.save(this.getRentDetailsObj(userId, bookId));	
						log.info(" Saved {} successfully  ", rentDetails);
						rentedBook = bookService.getBook(bookId);
						log.info(" Drawing a book  {}   ", rentedBook);
					}
					
				}		
			}			
		}
	
		return rentedBook;
	}
	
	private Boolean isEnoughBooksAvailableOnShelf(Integer availableBookCount, Long userId, Long bookId) {
		Boolean flag = true;
		Integer rentedBookCount = rentDetailsRepository.findRentableStatusByBookId(bookId);
		Integer bookAvailability = availableBookCount - rentedBookCount;
		if(bookAvailability <= 0 ) {
			log.info(" Currently, Shelf don't have any book to rent out !!! ");
			flag = false;
		}
		return flag;
	}
	
	private Boolean isUserAllowToRent(Long userId) {
		Boolean flag = true;
		User user = userService.getUser(userId);
		if (user == null  || user.getFine() >= 100) {
			log.info(" Hey user, you may not be from this planet or your have pending dues !!! ");
			flag = false;
		}
		
		if(flag) {
			Integer currentBorrowing = rentDetailsRepository.findRentableStatusOfUser(userId);
			if(currentBorrowing >= 2 ) {
				log.info(" Hey user, you have already reached to maximum borrowing limit !!! ");
				flag = false;
			}
		}
		
		return flag;
	}
	
	private RentDetails getRentDetailsObj(Long userId, Long bookId) {
		log.info(" Enterd getRentDetailsObj ");
		LocalDate today = LocalDate.now();
		LocalDate expectedReturnDate = today.plusDays(7);
		RentDetails rentDetails = new RentDetails(null, userId, bookId, 0, expectedReturnDate, null);
		return rentDetails;
	}
	
	@Transactional
	public Integer submitBook(Long userId, Long bookId) {
		log.info(" Enterd submitBook ");	
		
		Integer updateStatus = 0;
		RentDetails rentDetails = rentDetailsRepository.findRentDetailsByUserBookStatus(userId, bookId);
		if(rentDetails != null) {
			log.info(" updating the rent details {} " , rentDetails);
			LocalDate actualReturnDate = LocalDate.now();
			updateStatus = rentDetailsRepository.updateBookStatusAndActualReturnDate(userId, bookId, actualReturnDate);
			
			Integer delayDays = actualReturnDate.compareTo(rentDetails.getExpectedReturnDate());
			if(delayDays > 0) {
				log.info(" Hey user, you got delayed by {} " , delayDays);
				User user = userService.getUser(userId);
				user.updateFine(10 * delayDays);
				userService.updateUser(user);
			}		
			updateStatus = 1;
		}
	
		return updateStatus;
	}

}
