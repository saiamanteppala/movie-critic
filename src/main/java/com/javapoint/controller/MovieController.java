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

import com.javapoint.entities.Movie;
import com.javapoint.repository.MovieRepo;
import com.javapoint.service.MovieServices;


@RestController
@RequestMapping("/api")
public class MovieController {
	
	
	@Autowired
	MovieRepo movieRepo;
	
	@Autowired
	MovieServices movieServices;

	
	@PostMapping("/save/movieDetails")
	public Movie saveMovieDetails(@RequestBody Movie movie)
	{
	Movie movDetails=movieServices.saveMovieDetails(movie);
		return movDetails;
	}
	
	
	@GetMapping("/getAllMovieDetails")
	public List<Movie>getAllMovieDetails()
	{
		List<Movie> movDetails=movieServices.getAllMovieDetails();
		return movDetails;
	}
	
	
	@GetMapping("/getMovieDetailsById/{id}")
	public Movie getMovieDetailsById(@PathVariable int id)
	{
		Movie movDetails=movieServices.getMovieDetailsById(id);
		return movDetails;
	}
	
	
	@DeleteMapping("/deleteMovieById/{id}")
	public void deleteRoleById(@PathVariable int id)
	{
	movieServices.deleteMovieById(id);
	}
	
	
	@PutMapping("/updateMovieDetails")
	public Movie updateMovieDetails(@RequestBody Movie movie)
	{
		return movieServices.updateMovieDetails(movie);
	}
	
}
	
	
        