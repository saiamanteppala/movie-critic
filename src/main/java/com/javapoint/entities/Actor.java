package com.javapoint.entities;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="actor")
@Table

public class Actor {

	
	 @Id
		
		  @SequenceGenerator(name = "actor_id_sequence", sequenceName =
		  "actor_id_sequence", allocationSize = 1)
		  
		  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
		  "actor_id_sequence")
		 
	    @Column(name = "actor_id", updatable = false)
	 	private  int actor_id;
	 		
	 
	 	@Column
	 	private String actor_name;
	
		
		
		  @ManyToMany(mappedBy = "actor", fetch=FetchType.LAZY) private Set<Movie>
		  movie;
		 

		public Actor(int actor_id, String actor_name) {
			super();
			this.actor_id = actor_id;
			this.actor_name = actor_name;
		}

		public Actor() {
			
		}

		@Override
		public String toString() {
			return "Actor [actor_id=" + actor_id + ", actor_name=" + actor_name + "]";
		}

		public int getActor_id() {
			return actor_id;
		}

		public void setActor_id(int actor_id) {
			this.actor_id = actor_id;
		}

		public String getActor_name() {
			return actor_name;
		}

		public void setActor_name(String actor_name) {
			this.actor_name = actor_name;
		}
		

	
}
