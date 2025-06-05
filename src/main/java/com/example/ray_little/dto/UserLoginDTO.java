package com.example.ray_little.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
  @JsonProperty("user_name")
  @NotBlank(message = "user's name is required")
  private String userName;

  @NotBlank(message = "password is required!")
  private String password;
}
