package com.github.Jbreno.dscatalog.services;

import com.github.Jbreno.dscatalog.dto.CategoryDTO;
import com.github.Jbreno.dscatalog.dto.ProductDTO;
import com.github.Jbreno.dscatalog.entities.Category;
import com.github.Jbreno.dscatalog.entities.Product;
import com.github.Jbreno.dscatalog.projections.ProductProjection;
import com.github.Jbreno.dscatalog.repositories.CategoryRepository;
import com.github.Jbreno.dscatalog.repositories.ProductRepository;
import com.github.Jbreno.dscatalog.services.exceptions.DatabaseException;
import com.github.Jbreno.dscatalog.services.exceptions.ResourceNotFoundException;
import com.github.Jbreno.dscatalog.util.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> list = repository.findAll(pageable);
        return list.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product product = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        copyDtoToEntity(dto, product);
        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = repository.getReferenceById(id);
            copyDtoToEntity(dto, product);
            product = repository.save(product);
            return new ProductDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
        try{
            repository.deleteById(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setDate(dto.getDate());
        product.setImgUrl(dto.getImgUrl());

        product.getCategories().clear();

        for (CategoryDTO categoryDTO : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(categoryDTO.getId());
            product.getCategories().add(category);
        }
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPage(String name, String categoryId, Pageable pageable) {
        List<Long> categoryIds = Arrays.asList();
        if(!"0".equals(categoryId)) {
            categoryIds = Arrays.asList(categoryId.split(","))
                    .stream().map(Long::parseLong).toList();
        }
        Page<ProductProjection> page = repository.searchProducts(categoryIds, name, pageable);
        List<Long> productIds = page.map(x -> x.getId()).toList();
        List<Product> entities = repository.searchProductsWithCategories(productIds);
        entities = Utils.replace(page.getContent(), entities);
        List<ProductDTO> dtos = entities.stream().map(x -> new ProductDTO(x, x.getCategories())).toList();
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
}
