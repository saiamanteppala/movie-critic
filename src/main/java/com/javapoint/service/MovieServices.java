package com.javapoint.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.javapoint.entities.Movie;

import com.javapoint.repository.MovieRepo;


@Service
	public class MovieServices {
	@Autowired
	 private MovieRepo movieRepo;
	
	
	
	public void saveMoviePoster(MultipartFile file) {
		Movie movie = new Movie();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.contains(".."))
		{
			System.out.println("not a valid file");
		}
		try {
			movie.setMovie_poster(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		movieRepo.save(movie);
	}
	

	
	public Movie saveMovieDetails(Movie movie) {
		return movieRepo.save(movie);

}
	public List<Movie> getAllMovieDetails() {
	
	return (List<Movie>) movieRepo.findAll();
}

	public  Movie getMovieDetailsById(int id) {
	return movieRepo.findById(id);
}
	public void deleteMovieById(int id) {
	movieRepo.deleteById(id);	
}
	public Movie updateMovieDetails(Movie movie) {
	return movieRepo.save(movie);
}


}