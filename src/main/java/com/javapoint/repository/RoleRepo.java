package com.javapoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.javapoint.entities.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {

Role findById(int id);
}

	


