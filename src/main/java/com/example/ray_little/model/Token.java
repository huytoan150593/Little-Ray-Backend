package com.example.ray_little.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @NotNull(message = "User is required")
  private User user;

  @Column(name = "token")
  private String token;

  @Column(name = "token_type", nullable = false, length = 50)
  @NotBlank(message = "Token type is required")
  @Size(max = 50, message = "Token type must be at most 50 characters")
  private String tokenType;

  @Column(name = "expired_at", nullable = false)
  @NotNull(message = "Expiration date is required")
  @Future(message = "Expiration date must be in the future")
  private Instant expiresAt;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(nullable = false)
  @NotNull(message = "Revoked status is required")
  private Boolean revoked;

  @Column(name = "revoked_at")
  private Instant revokedAt;

  @Column(name = "device_info")
  private String deviceInfo;

  @Column(name = "ip_address", length = 45)
  @Size(max = 45, message = "IP address must be at most 45 characters")
  private String ipAddress;
}
