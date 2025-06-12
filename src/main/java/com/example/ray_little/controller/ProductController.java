package com.example.ray_little.controller;

import com.example.ray_little.dto.ProductDTO;
import com.example.ray_little.service.ProductServiceImp;
import com.example.ray_little.utils.SortUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductServiceImp productServiceImp;

  @PostMapping("")
  public ResponseEntity<?> createProduct(
    @Valid @RequestBody ProductDTO productDTO,
    BindingResult result){
    if(result.hasErrors()){
      List<String> errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
      return ResponseEntity.badRequest().body(errorMsg);
    }
    productServiceImp.createProduct(productDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
  }

  @GetMapping("")
  public ResponseEntity<Page<ProductDTO>> getProducts(
    @RequestParam(required = false) Long categoryId,
    @RequestParam(required = false) Double minPrice,
    @RequestParam(required = false) Double maxPrice,
    @RequestParam(required = false) Boolean bestSeller,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id,asc") String sort
  ) {
    Pageable pageable = PageRequest.of(page, size, SortUtil.parseSort(sort));
    Page<ProductDTO> result = productServiceImp.filterProducts(categoryId, minPrice, maxPrice, pageable);
    return ResponseEntity.ok(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateProduct(@PathVariable long id, @Valid @RequestBody ProductDTO productDTO,
                                                 BindingResult result){

    productServiceImp.updateProduct(id, productDTO);
    return ResponseEntity.ok("Update Product with id = " + id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable long id){
    productServiceImp.deleteProduct(id);
    return ResponseEntity.ok("Delete Product with id = " + id);
  }
}
