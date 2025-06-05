package com.example.ray_little.service;

import com.example.ray_little.component.JwtTokenUtil;
import com.example.ray_little.dto.UserDTO;
import com.example.ray_little.exception.DataNotFoundException;
import com.example.ray_little.model.Role;
import com.example.ray_little.model.User;
import com.example.ray_little.repository.RoleRepository;
import com.example.ray_little.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtTokenUtil;
  private final AuthenticationManager authenticationManager;

  @Override
  public User register(UserDTO userDTO) throws DataNotFoundException {
    String userName = userDTO.getUserName();
    // Check phone number already register or not
    if(userRepository.existsByUserName(userName)){
      throw new DataIntegrityViolationException("User already exists");
    }

    // Convert userDTO => user
    User newUser = User.builder()
                     .userName(userDTO.getUserName())
                     .phoneNumber(userDTO.getPhoneNumber())
                     .password(userDTO.getPassword())
                     .address(userDTO.getAddress())
                     .dateOfBirth(userDTO.getDateOfBirth())
                     .facebookAccountId(userDTO.getFacebookAccountId())
                     .googleAccountId(userDTO.getGoogleAccountId())
                     .build();
    Role role = roleRepository.findById(userDTO.getRoleId())
                            .orElseThrow(() -> new DataNotFoundException("Role not found"));
    newUser.setRole(role);

    // If user register by Facebook or Google account
    if(userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0){
      // code here
      String password = userDTO.getPassword();
      String encodedPassword = passwordEncoder.encode(password);
      newUser.setPassword(encodedPassword);
    }
    return userRepository.save(newUser);
  }

  @Override
  public String login(String userName, String password) throws Exception {
    Optional<User> optionalUser = userRepository.findByUserName(userName);
    if(optionalUser.isEmpty()){
      throw new DataNotFoundException("Invalid userName / password");
    }
    // authenticate with Java Spring security
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
      userName, password
    );
    authenticationManager.authenticate(authenticationToken);

    return jwtTokenUtil.generateToken(optionalUser.get());
  }
}
