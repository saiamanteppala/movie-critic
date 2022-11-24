package com.javapoint.entities;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;





	
	@Entity (name= "movie")
	@Table
	public class Movie {
		
		@Id

		@GeneratedValue(strategy = GenerationType.AUTO)
		private int movie_id;
		
		 @Column
		private String movie_name;
		 
		
		 @Column 
		 private String movie_categories;
		 
		 
		 
		 @Column
		 private String movie_language;
		
		 @Column
		 private String movie_genres;
		
		 @Column
	     private Date release_date;

		 @Column
		 private LocalTime movie_duration;
		 
		 @Lob
		 @Column(columnDefinition = "MEDIUMBLOB")
		 private String movie_poster;
		
			
			
			  @OneToMany(mappedBy = "movie", fetch=FetchType.LAZY) 
			  private List<Review> review;
			  
			  
			  @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
			  
			  @JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name =
			  "movie_id") }, inverseJoinColumns = {
			  
			  @JoinColumn(name = "actor_id") }) private Set<Actor> actor;
			 

		public Movie(int movie_id, String movie_name, String movie_categories, String movie_language,
				String movie_genres, Date release_date, LocalTime movie_duration,String movie_poster) {
			super();
			this.movie_id = movie_id;
			this.movie_name = movie_name;
			this.movie_categories = movie_categories;
			this.movie_language = movie_language;
			this.movie_genres = movie_genres;
			this.release_date = release_date;
			this.movie_duration = movie_duration;
			this.movie_poster = movie_poster;
		}

		public Movie() {
			
			
		}

		public String getMovie_poster() {
			return movie_poster;
		}

		public void setMovie_poster(String movie_poster) {
			this.movie_poster = movie_poster;
		}

		public int getMovie_id() {
			return movie_id;
		}

		public void setMovie_id(int movie_id) {
			this.movie_id = movie_id;
		}

		public String getMovie_name() {
			return movie_name;
		}

		public void setMovie_name(String movie_name) {
			this.movie_name = movie_name;
		}

		
		  public String getMovie_categories() { return movie_categories; }
		  
		  public void setMovie_categories(String movie_categories) {
		  this.movie_categories = movie_categories; }
		 

		public String getMovie_language() {
			return movie_language;
		}

		public void setMovie_language(String movie_language) {
			this.movie_language = movie_language;
		}

		public String getMovie_genres() {
			return movie_genres;
		}

		public void setMovie_genres(String movie_genres) {
			this.movie_genres = movie_genres;
		}

		public Date getRelease_date() {
			return release_date;
		}

		public void setRelease_date(Date release_date) {
			this.release_date = release_date;
		}

		public LocalTime getMovie_duration() {
			return movie_duration;
		}

		public void setMovie_duration(LocalTime movie_duration) {
			this.movie_duration = movie_duration;
		}

		
}
