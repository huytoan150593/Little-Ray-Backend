package com.example.ray_little.service;

import com.example.ray_little.dto.ProductDTO;
import com.example.ray_little.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
  void createProduct(ProductDTO productDTO);

  Optional<Product> getProductById(Long productId);

  List<ProductDTO> getAllProduct();

  void updateProduct(Long productId, ProductDTO productDTO);

  void deleteProduct(Long productId);

//  Page<ProductDTO> getAllProductsPaged(int page, int size);

  Page<ProductDTO> filterProducts(Long categoryId, Double minPrice, Double maxPrice, Pageable pageable);
}
