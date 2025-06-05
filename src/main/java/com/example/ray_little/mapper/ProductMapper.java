package com.example.ray_little.mapper;

import com.example.ray_little.dto.CategoryDTO;
import com.example.ray_little.dto.ProductDTO;
import com.example.ray_little.model.Category;
import com.example.ray_little.model.Product;
import com.example.ray_little.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
  private final CategoryRepository categoryRepository;

  public Product toEntity(ProductDTO dto, Product product) {
    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    product.setThumbnail(dto.getThumbnail());
    product.setBestSeller(dto.isBestSeller());

    Category category = categoryRepository.findById(dto.getCategory().getId())
                          .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

    product.setCategory(category);

    return product;
  }

  public ProductDTO toDTO(Product product) {
    ProductDTO dto = new ProductDTO();
    CategoryDTO category = CategoryMapper.toDTO(product.getCategory());
    dto.setId(product.getId());
    dto.setName(product.getName());
    dto.setPrice(product.getPrice());
    dto.setThumbnail(product.getThumbnail());
    dto.setBestSeller(product.getBestSeller());
    dto.setCategory(category);
    return dto;
  }
}
