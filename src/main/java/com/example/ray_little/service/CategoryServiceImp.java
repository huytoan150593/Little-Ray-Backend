package com.example.ray_little.service;

import com.example.ray_little.dto.CategoryDTO;
import com.example.ray_little.model.Category;
import com.example.ray_little.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
  private final CategoryRepository categoryRepository;


  @Override
  public void createCategory(CategoryDTO categoryDTO) {
    Category newCategory = Category.builder()
      .name(categoryDTO.getName())
      .build();
    categoryRepository.save(newCategory);
  }

  @Override
  public Category getCategoryById(long id) {
    return categoryRepository.findById(id);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public void updateCategory(long categoryId, @NotNull CategoryDTO categoryDTO) {
    Category existing = getCategoryById(categoryId);
    existing.setName(categoryDTO.getName());
  }

  @Override
  public void deleteCategory(long id) {
    categoryRepository.deleteById(id);
  }
}
