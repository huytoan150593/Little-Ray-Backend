package com.example.ray_little.repository;

import com.example.ray_little.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findById(long id);
}
