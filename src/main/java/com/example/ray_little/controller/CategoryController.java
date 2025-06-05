package com.example.ray_little.controller;

import com.example.ray_little.dto.CategoryDTO;
import com.example.ray_little.model.Category;
import com.example.ray_little.service.CategoryServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryServiceImp categoryServiceImp;

  // Show all Categories
  @GetMapping("")
  public ResponseEntity<List<Category>> getAllCategories(){
    return ResponseEntity.ok(categoryServiceImp.getAllCategories());
  }

  @PostMapping("")
  public ResponseEntity<?> createCategories(
    @Valid @RequestBody CategoryDTO categoryDTO,
    BindingResult result){
    if(result.hasErrors()){
      List<String> errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
      return ResponseEntity.badRequest().body(errorMsg);
    }
    categoryServiceImp.createCategory(categoryDTO);
    return ResponseEntity.ok("Category Created Successful !!: " + categoryDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateCategories(@PathVariable long id, @Valid @RequestBody CategoryDTO categoryDTO,
                                                 BindingResult result){

    categoryServiceImp.updateCategory(id, categoryDTO);
    return ResponseEntity.ok("Update Categories with id = " + id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCategories(@PathVariable long id){
    categoryServiceImp.deleteCategory(id);
    return ResponseEntity.ok("Delete Categories with id = " + id);
  }
}
