package com.javapoint.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.javapoint.entities.Role;
import com.javapoint.repository.RoleRepo;

@Service

public class RoleService {
	@Autowired
	private RoleRepo roleRepo;
public Role saveRoleDetails(Role role) {
		return roleRepo.save(role);

}
public List<Role> getAllRoleDetails() {
	
	return (List<Role>) roleRepo.findAll();
}

public  Role getRoleDetailsById(int id) {
	return roleRepo.findById(id);
}
public void deleteRoleById(int id) {
	roleRepo.deleteById(id);	
}
public Role updateRoleDetails(Role role) {
	return roleRepo.save(role);
}
}


