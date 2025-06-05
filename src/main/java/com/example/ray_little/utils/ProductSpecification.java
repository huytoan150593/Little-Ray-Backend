package com.example.ray_little.utils;

import com.example.ray_little.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
  public static Specification<Product> filterBy(
    Long categoryId,
    Double minPrice,
    Double maxPrice
  ) {
    return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Predicate predicate = cb.conjunction();

      if (categoryId != null && categoryId != 0) {
        predicate = cb.and(predicate, cb.equal(root.get("category").get("id"), categoryId));
      }

      if (minPrice != null) {
        predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
      }

      if (maxPrice != null) {
        predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
      }

      return predicate;
    };
  }
}

