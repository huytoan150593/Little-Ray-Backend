package com.example.ray_little.repository;

import com.example.ray_little.model.Role;
import com.example.ray_little.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
