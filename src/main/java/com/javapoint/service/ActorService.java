package com.javapoint.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javapoint.entities.Actor;
import com.javapoint.repository.ActorRepo;

@Service

public class ActorService {
	@Autowired 
	private ActorRepo actorRepo;
	public Actor saveActorDetails(Actor actor)
	{
		return actorRepo.save(actor);
	}
	public List<Actor> getAllActorDetails() {
		
		return (List<Actor>)actorRepo.findAll();
	}
	
	
	public Actor  getActorDetailsById(int id) {
		
		return actorRepo.findById(id);
	}
	public void deleteActorById(int id) {
		actorRepo.deleteById(id);	
	}
	public Actor updateActorDetails(Actor actor) {
		return actorRepo.save(actor);
	}

	
	
}