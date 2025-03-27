package com.devsuperior.aula.Services;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.dto.ProductDTO;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        for (CategoryDTO catDto : dto.getCategories()) {
            Category cat = categoryRepository.getReferenceById(catDto.getId());
//            Category cat = new Category();
//            cat.setId(catDto.getId());
            entity.getCategories().add(cat);
        }

        entity = productRepository.save(entity);

        return new ProductDTO(entity);
    }
}
