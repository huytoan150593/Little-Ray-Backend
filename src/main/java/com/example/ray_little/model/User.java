package com.example.ray_little.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name", nullable = false, length = 50)
  @NotBlank(message = "Username is required")
  @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
  private String userName;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "phone_number", nullable = false, length = 20)
  @NotBlank(message = "Phone number is required")
  @Size(max = 20, message = "Phone number must be at most 20 characters")
  private String phoneNumber;

  @Column(name = "address", nullable = false)
  @NotBlank(message = "Address is required")
  private String address;

  @Column(name = "password")
  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
  private String password;

  @Column(name = "date_of_birth", nullable = false)
  @NotNull(message = "Date of birth is required")
  @Past(message = "Date of birth must be in the past")
  private Date dateOfBirth;

  @Column(name = "facebook_account_id", nullable = false)
  @PositiveOrZero(message = "Facebook account ID must be positive or zero")
  private Integer facebookAccountId;

  @Column(name = "google_account_id", nullable = false)
  @PositiveOrZero(message = "Google account ID must be positive or zero")
  private Integer googleAccountId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", nullable = false)
  @NotNull(message = "Role is required")
  private Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority("ROLE_" + getRole().getName()));
    return authorityList;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
