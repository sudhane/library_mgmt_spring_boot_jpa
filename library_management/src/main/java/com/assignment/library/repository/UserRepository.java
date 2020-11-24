package com.assignment.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.library.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
