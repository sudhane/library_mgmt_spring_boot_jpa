package com.assignment.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.library.model.Book;
import com.assignment.library.repository.BookRepository;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;

	public Book getBook(Long bookId) {
		Book book = null;
		if(bookId != null) {
			book = bookRepository.findById(bookId).get();
		}
		return book; 
	}
}
