package com.example.ray_little.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @Min(value = 0, message = "Price must be greater than or equal to 0")
  private double price = 0;

  @NotBlank(message = "Image URL cannot be blank")
  private String thumbnail;

  @Column(name = "best_seller")
  private Boolean bestSeller;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  // Constructors, Getters, Setters
}
