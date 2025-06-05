package com.example.ray_little.component;

import com.example.ray_little.model.Category;
import com.example.ray_little.model.Product;
import com.example.ray_little.repository.CategoryRepository;
import com.example.ray_little.repository.ProductRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class ProductSeeder implements CommandLineRunner {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public ProductSeeder(ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void run(String... args) {

    if (productRepository.count() <= 100) {
      Faker faker = new Faker(new Locale("en"));
      List<Category> categories = categoryRepository.findAll();

      for (int i = 0; i < 100; i++) {
        Product product = new Product();
        product.setName(faker.commerce().productName());
        product.setPrice(Double.parseDouble(faker.commerce().price(10.0, 250.0)));
        product.setThumbnail("https://picsum.photos/208/260?random=" + i);
        product.setBestSeller(faker.bool().bool());
        product.setCategory(categories.get(faker.random().nextInt(categories.size())));

        productRepository.save(product);
      }

      System.out.println("🌱 Seeded 3 categories and 100 fake products.");
    }
  }
}

