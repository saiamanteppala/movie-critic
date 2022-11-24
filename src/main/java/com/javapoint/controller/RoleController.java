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


import com.javapoint.entities.Role;
import com.javapoint.service.RoleService;


@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	@PostMapping("/save/_RoleDetails")
	public Role saveRoleDetails(@RequestBody Role role)
	{
	Role rolDetails=roleService.saveRoleDetails(role);
		return rolDetails;
	}
	@GetMapping("/_getAllRoleDetails")
	public List<Role>getAllRoleDetails()
	{
		List<Role> rolDetails=roleService.getAllRoleDetails();
		return rolDetails;
	}
	@GetMapping("/_getRoleDetailsById/{id}")
public Role getRoleDetailsById(@PathVariable int id)
{
		Role rolDetails=roleService.getRoleDetailsById(id);
		return rolDetails;
		
		}
	@DeleteMapping("/deleteRoleById/{id}")
	public void deleteRoleById(@PathVariable int id)
	{
		roleService.deleteRoleById(id);
	}
	@PutMapping("/updateRoleDetails")
	public Role updateRoleDetails(@RequestBody Role role)
	{
		return roleService.updateRoleDetails(role);
	}
	

}
