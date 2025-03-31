package com.github.JBreno.dscommerce.repositories;

import com.github.JBreno.dscommerce.entities.Order;
import com.github.JBreno.dscommerce.entities.OrderItem;
import com.github.JBreno.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
