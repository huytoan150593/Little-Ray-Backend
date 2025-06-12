package com.example.ray_little.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

  private Long id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @Min(value = 0, message = "Price must be greater than or equal to 0")
  private double price = 0;

  @NotBlank(message = "Image URL cannot be blank")
  private String thumbnail;

  @JsonProperty("best_seller")
  private boolean bestSeller;

  @JsonProperty("category_id")
  @NotNull(message = "Category is required")
  private Long categoryId;

  private CategoryDTO category;

  // Constructors, Getters, Setters
}
