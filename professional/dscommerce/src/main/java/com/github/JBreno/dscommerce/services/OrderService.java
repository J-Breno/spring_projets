package com.github.JBreno.dscommerce.services;

import com.github.JBreno.dscommerce.dto.OrderDTO;
import com.github.JBreno.dscommerce.dto.ProductDTO;
import com.github.JBreno.dscommerce.entities.Order;
import com.github.JBreno.dscommerce.entities.Product;
import com.github.JBreno.dscommerce.repositories.OrderRepository;
import com.github.JBreno.dscommerce.repositories.ProductRepository;
import com.github.JBreno.dscommerce.services.exceptions.ResouceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Recurso não encontrado")
        );
        return new OrderDTO(order);
    }
}
