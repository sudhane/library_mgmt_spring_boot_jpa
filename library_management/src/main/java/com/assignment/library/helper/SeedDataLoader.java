package com.assignment.library.helper;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.assignment.library.model.Book;
import com.assignment.library.model.Shelf;
import com.assignment.library.model.User;
import com.assignment.library.repository.BookRepository;
import com.assignment.library.repository.RentDetailsRepository;
import com.assignment.library.repository.ShelfRepository;
import com.assignment.library.repository.UserRepository;

@Component
@Profile("!test")
public class SeedDataLoader implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(SeedDataLoader.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	ShelfRepository shelfRepository;
	
	@Autowired
	RentDetailsRepository rentDetailsRepository;
	
	@Override
    public void run(String... args) throws Exception {
		
		log.info("Entered SeedData Loader ");
		
//		userRepository.deleteAll();
//		bookRepository.deleteAll();
//		shelfRepository.deleteAll();
		
		//Add few users	
		userRepository.saveAll(Arrays.asList(new User(null, "Sujit", 0), new User(null, "Saarthak", 0), new User(null, "Hariom", 0)));
		
		//Add few books	
		Book book1 = new Book(null, "Harry Potter", "JK Rowling", "Mann");
		Book book2 = new Book(null, "Master Chef", "Anna Swami", "Qute");
		Book book3 = new Book(null, "ML Champs", "Sam", "BNL");
		bookRepository.saveAll(Arrays.asList(book1, book2, book3));
		
		//Shelf Inventory	
		shelfRepository.saveAll(Arrays.asList(new Shelf(null, book1.getId(), 4), new Shelf(null, book2.getId(), 8), 
				new Shelf(null, book3.getId(), 1)));		

		
	}

}
