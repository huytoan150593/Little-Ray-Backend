package com.example.ray_little.service;

import com.example.ray_little.dto.ProductDTO;
import com.example.ray_little.mapper.ProductMapper;
import com.example.ray_little.model.Product;
import com.example.ray_little.repository.ProductRepository;
import com.example.ray_little.utils.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;

  @Override
  public void createProduct(ProductDTO productDTO) {

    Product newProduct = productMapper.toEntity(productDTO, new Product());
    productRepository.save(newProduct);
  }

  @Override
  public Optional<Product> getProductById(Long productId) {
    return productRepository.findById(productId);
  }

  @Override
  public List<ProductDTO> getAllProduct() {
    List<Product> products = productRepository.findAll();
    return products.stream()
             .map(productMapper::toDTO)
             .collect(Collectors.toList());
  }

  public Page<ProductDTO> filterProducts(Long categoryId, Double minPrice, Double maxPrice, Pageable pageable) {
    Specification<Product> spec = ProductSpecification.filterBy(categoryId, minPrice, maxPrice);

    Page<Product> productPage = productRepository.findAll(spec, pageable);

    return productPage.map(productMapper::toDTO);
  }


  @Override
  public void updateProduct(Long productId, ProductDTO productDTO) {
    Optional<Product> optionalProduct = productRepository.findById(productId);
    if(optionalProduct.isPresent()){
      Product existingProduct = productMapper.toEntity(productDTO, optionalProduct.get());
      productRepository.save(existingProduct);
    }
  }

  @Override
  public void deleteProduct(Long productId) {
    productRepository.deleteById(productId);
  }
}
