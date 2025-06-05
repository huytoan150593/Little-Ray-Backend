package com.example.ray_little.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  @JsonProperty("user_name")
  private String userName;

  @JsonProperty("phone_number")
  @NotNull(message = "phone number is required!")
  private String phoneNumber;

  @NotBlank(message = "password is required!")
  private String password;

  @JsonProperty("retype_password")
  private String retypePassword;

  private String address;

  @JsonProperty("date_of_birth")
  private Date dateOfBirth;

  @JsonProperty("facebook_account_id")
  private int facebookAccountId;

  @JsonProperty("google_account_id")
  private  int googleAccountId;

  @NotNull(message = "Role Id is required")
  @JsonProperty("role_id")
  private Long roleId;
}
