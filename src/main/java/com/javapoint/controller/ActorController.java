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

import com.javapoint.entities.Actor;

import com.javapoint.service.ActorService;

@RestController
@RequestMapping("/api")

public class ActorController {
	@Autowired
	ActorService actorService;
	@PostMapping("/save/_ActorDetails")
	public Actor saveActorDetails(@RequestBody Actor actor)
	{
		Actor actDetails=actorService.saveActorDetails(actor);
		return actDetails;
	}
	@GetMapping("/_getAllActorDetails")
	public List<Actor>getAllActorDetails()
	{
		List<Actor> actDetails=actorService.getAllActorDetails();
		return actDetails;
	}
	@GetMapping("/_getActorDetailsById/{id}")
public Actor getActorDetailsById(@PathVariable int id)
{
		Actor actDetails=actorService.getActorDetailsById(id);
		return actDetails;
}
	@DeleteMapping("/deleteActorById/{id}")
	public void deleteMovieById(@PathVariable int id)
	{
		actorService.deleteActorById(id);
	}
	@PutMapping("/updateActorDetails")
	public Actor updateActorDetails(@RequestBody Actor actor)
	{
		return actorService.updateActorDetails(actor);
	}
	
	
	
}
