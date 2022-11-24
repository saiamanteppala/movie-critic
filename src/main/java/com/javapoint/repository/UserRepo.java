package com.javapoint.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;


import com.javapoint.entities.User;




@Repository

	public interface UserRepo extends JpaRepository<User, Integer> {

	User findById(int id);
	
	 User findByEmail(String email);
	
	
	 
}
