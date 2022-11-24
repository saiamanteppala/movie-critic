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


import com.javapoint.entities.User;
import com.javapoint.service.UserService;




@RestController
@RequestMapping("/api")
public class UserController {

	
	@Autowired
	UserService userService;
	@PostMapping("/save/UserDetails")
	public User saveUserDetails(@RequestBody User user)
	{
		User useDetails=userService.saveUserDetails(user);
		return useDetails;
	}
	@GetMapping("/getAllUserDetails")
	public List<User>getAllUserDetails()
	{
		List<User> useDetails=userService.getAllUserDetails();
		return useDetails;
	}
	@GetMapping("/getUserDetailsById/{id}")
public User getUserDetailsById(@PathVariable int id)
{
		User useDetails=userService.getUserDetailsById(id);
		return useDetails;
}
	@DeleteMapping("/deleteUserById/{id}")
	public void deleteUserById(@PathVariable int id)
	{
		userService.deleteUserById(id);
	}
	@PutMapping("/updateUserDetails")
	public User updateUserDetails(@RequestBody User user)
	{
		return userService.updateUserDetails(user);
	}
	
	 @DeleteMapping("/deleteUser") public void deleteUser(@RequestBody User user)
	  { userService.deleteUser(user); }
}
	
	
	