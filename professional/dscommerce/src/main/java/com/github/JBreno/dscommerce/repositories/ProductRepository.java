package com.github.JBreno.dscommerce.repositories;

import com.github.JBreno.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
