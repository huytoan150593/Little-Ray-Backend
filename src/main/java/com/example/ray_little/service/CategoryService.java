package com.example.ray_little.service;

import com.example.ray_little.dto.CategoryDTO;
import com.example.ray_little.model.Category;

import java.util.List;

public interface CategoryService {
  void createCategory(CategoryDTO categoryDTO);
  Category getCategoryById(long id);
  List<Category> getAllCategories();
  void updateCategory(long categoryId, CategoryDTO categoryDTO);
  void deleteCategory(long id);
}
