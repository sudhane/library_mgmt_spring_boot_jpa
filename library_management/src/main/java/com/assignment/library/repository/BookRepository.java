package com.assignment.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
