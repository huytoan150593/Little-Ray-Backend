package com.example.ray_little.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

  private Long id;

  @NotEmpty(message = "Category's name cannot be empty")
  private String name;

  private String description;
}
