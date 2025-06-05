package com.example.ray_little.service;

import com.example.ray_little.dto.UserDTO;
import com.example.ray_little.exception.DataNotFoundException;
import com.example.ray_little.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  User register(UserDTO userDTO) throws DataNotFoundException;

  String login(String phoneNumber, String password) throws Exception;
}
