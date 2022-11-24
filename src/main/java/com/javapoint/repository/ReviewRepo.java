package com.javapoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.javapoint.entities.Review;


@Repository
public interface ReviewRepo extends JpaRepository<Review,Integer> {
	
Review findById(int id);


}
