package com.example.ray_little.repository;

import com.example.ray_little.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByPhoneNumber(String phoneNumber);

  boolean existsByUserName(String userName);

  Optional<User> findByPhoneNumber(String phoneNumber);

  Optional<User> findByUserName(String userName);
}
