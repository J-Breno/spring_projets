package com.github.Jbreno.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.Jbreno.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repository;
	
	  private long exintingId;
	    private long nonExintingId;
	    private long countTotalProducts;
	    
	    @BeforeEach
	    void setUp() throws Exception {
	    	exintingId = 1L;
	    	nonExintingId = 1000L;
	    	countTotalProducts = 25L;
	    }
	    
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Product product = new Product();
		product.setId(null);
		
		product = repository.save(product);
		
		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts + 1 , product.getId());
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(exintingId);
		
		Optional<Product> result = repository.findById(exintingId);
		
		Assertions.assertFalse(result.isPresent());
	}
}
