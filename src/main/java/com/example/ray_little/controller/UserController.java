package com.example.ray_little.controller;

import com.example.ray_little.dto.UserDTO;
import com.example.ray_little.dto.UserLoginDTO;
import com.example.ray_little.exception.DataNotFoundException;
import com.example.ray_little.service.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
  public final UserServiceImp userServiceImp;

  @PostMapping("/register")
  public ResponseEntity<?> register(
    @Valid @RequestBody UserDTO userDTO,BindingResult result) throws DataNotFoundException {
    // Check Error
    if(result.hasErrors()){
      List<String> errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
      return ResponseEntity.badRequest().body(errorMsg);
    }
    if(!userDTO.getPassword().equals(userDTO.getRetypePassword())){
      return ResponseEntity.badRequest().body("Retype Password not match!");
    }

    // Register
    userServiceImp.register(userDTO);
    return ResponseEntity.ok(Map.of("messages from server: ", "Register Successful. Login Now"));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(
    @Valid @RequestBody UserLoginDTO userLoginDTO) {
    try{
      // Login
      String token = userServiceImp.login(userLoginDTO.getUserName(), userLoginDTO.getPassword());
      return ResponseEntity.ok(Map.of("token", token));
    }catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
