package com.javapoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.javapoint.entities.Review;

import com.javapoint.repository.ReviewRepo;
@Service

public class ReviewService {
	
	@Autowired 
	private ReviewRepo reviewRepo;
	public Review saveReviewDetails(Review review)
	{
		return reviewRepo.save(review);
	}
	public List<Review> getAllReviewDetails() {
		
		return (List<Review>)reviewRepo.findAll();
	}
	
	
	public Review  getReviewDetailsById(int id) {
		
		return reviewRepo.findById(id);
	}
	public void deleteReviewById(int id) {
		reviewRepo.deleteById(id);	
	}
	public Review updateReviewDetails(Review review) {
		return reviewRepo.save(review);
	}

	
	
	
	

}
