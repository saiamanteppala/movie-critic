package com.javapoint.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.javapoint.entities.Movie;

@Repository
public interface MovieRepo  extends JpaRepository<Movie, Integer>{
	
	Movie findById(int id);
	
}
