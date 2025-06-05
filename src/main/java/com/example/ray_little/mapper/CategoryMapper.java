package com.example.ray_little.mapper;

import com.example.ray_little.dto.CategoryDTO;
import com.example.ray_little.model.Category;

public class CategoryMapper {

  // Entity → DTO
  public static CategoryDTO toDTO(Category category) {
    if (category == null) return null;

    CategoryDTO dto = new CategoryDTO();
    dto.setId(category.getId());
    dto.setName(category.getName());
    dto.setDescription(category.getDescription());
    return dto;
  }

  // DTO → Entity
  public static Category toEntity(CategoryDTO dto) {
    if (dto == null) return null;

    Category category = new Category();
    category.setId(dto.getId());
    category.setName(dto.getName());
    category.setDescription(dto.getDescription());
    return category;
  }
}

