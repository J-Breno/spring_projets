package com.github.JBreno.dscommerce.repositories;

import com.github.JBreno.dscommerce.entities.Order;
import com.github.JBreno.dscommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
