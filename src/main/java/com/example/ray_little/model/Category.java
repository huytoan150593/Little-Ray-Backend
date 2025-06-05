package com.example.ray_little.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  private String description;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

}
