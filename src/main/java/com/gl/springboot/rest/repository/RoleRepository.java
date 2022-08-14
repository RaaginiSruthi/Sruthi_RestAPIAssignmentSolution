package com.gl.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.springboot.rest.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
