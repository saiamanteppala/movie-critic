package com.javapoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.javapoint.entities.Actor;
@Repository
public interface ActorRepo extends JpaRepository<Actor,Integer> {
	
Actor findById(int id);


}
