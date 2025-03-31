package com.github.JBreno.dscommerce.controllers;

import com.github.JBreno.dscommerce.dto.CategoryDTO;
import com.github.JBreno.dscommerce.dto.ProductDTO;
import com.github.JBreno.dscommerce.dto.ProductMinDTO;
import com.github.JBreno.dscommerce.services.CategoryService;
import com.github.JBreno.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }
}
