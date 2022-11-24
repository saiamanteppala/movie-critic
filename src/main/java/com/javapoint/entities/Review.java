package com.javapoint.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity (name= "review")
@Table
public class Review {


		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int review_id;
	
	  @Column(name = "movie_reviews", nullable = false)
	 
      private String movie_reviews;
	  
	  
	  @Column(name = "movie_ratings", nullable = false)
	  private double movie_ratings;
	  
	  
	  
	  @Column(name = "actor_name", updatable = false)
	  private String actor_name;
	  
	  
		
		@ManyToOne(fetch=FetchType.LAZY)
	     @JoinColumn(name = "movie_name", updatable = false)
		  private Movie movie;

	

	public Review(int review_id, String movie_reviews, double movie_ratings, String actor_name,
				Movie movie_name) {
			super();
			this.review_id = review_id;
			this.movie_reviews = movie_reviews;
			this.movie_ratings = movie_ratings;
			this.actor_name = actor_name;
			this.movie = movie_name;
		}



	public Review() {
		
	}



	public int getReview_id() {
		return review_id;
	}



	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}



	public String getMovie_reviews() {
		return movie_reviews;
	}



	public void setMovie_reviews(String movie_reviews) {
		this.movie_reviews = movie_reviews;
	}



	public double getMovie_ratings() {
		return movie_ratings;
	}



	public void setMovie_ratings(double movie_ratings) {
		this.movie_ratings = movie_ratings;
	}



	public String getActor_name() {
		return actor_name;
	}



	public void setActor_name(String actor_name) {
		this.actor_name = actor_name;
	}



	public Movie getMovie() {
		return movie;
	}



	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}