package com.github.Jbreno.dscatalog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.Jbreno.dscatalog.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServicesTests {
	
	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	private long existingId;
	
	@BeforeEach
	void setUp() throws	Exception {
		existingId = 1L;
		
		Mockito.doNothing().when(repository).deleteById(existingId);;
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository).deleteById(existingId);
	}
}
