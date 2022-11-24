package com.javapoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javapoint.entities.Review;
import com.javapoint.service.ReviewService;
@RestController
@RequestMapping("/api")

public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	
	@PostMapping("/save/ReviewDetails")
	public Review saveReviewDetails(@RequestBody Review review)
	{
		Review revDetails=reviewService.saveReviewDetails(review);
		return revDetails;
	}
	@GetMapping("/getAllReviewDetails")
	public List<Review>getAllReviewDetails()
	{
		List<Review> revDetails=reviewService.getAllReviewDetails();
		return revDetails;
	}
	@GetMapping("/getReviewDetailsById/{id}")
public Review getReviewDetailsById(@PathVariable int id)
{
		Review revDetails=reviewService.getReviewDetailsById(id);
		return revDetails;
}
	@DeleteMapping("/deleteReviewById/{id}")
	public void deleteReviewById(@PathVariable int id)
	{
		reviewService.deleteReviewById(id);
	}
	@PutMapping("/updateReviewDetails")
	public Review updateReviewDetails(@RequestBody Review review)
	{
		return reviewService.updateReviewDetails(review);
	}
	
	
	
}


