package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.services.exceptions.DatabaseException;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {
	
	@InjectMocks
	private MovieService service;

	@Mock
	private MovieRepository movieRepository;

	private String title;
	private PageImpl page;
	private MovieEntity movie;
	private Long existId, nonExistId, dependentId;
	private MovieDTO movieDTO;

	@BeforeEach
	public void setUp() throws Exception {
		existId = 1L;
		nonExistId = 2L;
		dependentId = 3L;
		title = "Django Livre";
		movie = MovieFactory.createMovieEntity();
		movieDTO = MovieFactory.createMovieDTO();

		page = new PageImpl<>(List.of(movie));

		Mockito.when(movieRepository.searchByTitle(any(),(Pageable) any())).thenReturn(page);

		Mockito.when(movieRepository.findById(existId)).thenReturn(Optional.of(movie));
		Mockito.when(movieRepository.findById(nonExistId)).thenReturn(Optional.empty());

		Mockito.when(movieRepository.save(any())).thenReturn(movie);

		Mockito.when(movieRepository.getReferenceById(existId)).thenReturn(movie);
		Mockito.when(movieRepository.getReferenceById(nonExistId)).thenThrow(EntityNotFoundException.class);

		Mockito.doNothing().when(movieRepository).deleteById(existId);
		Mockito.doThrow(EntityNotFoundException.class).when(movieRepository).deleteById(nonExistId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(movieRepository).deleteById(dependentId);

		Mockito.when(movieRepository.existsById(existId)).thenReturn(true);
		Mockito.when(movieRepository.existsById(nonExistId)).thenReturn(false);
		Mockito.when(movieRepository.existsById(dependentId)).thenReturn(true);
	}
	
	@Test
	public void findAllShouldReturnPagedMovieDTO() {
		PageRequest pageRequest = PageRequest.of(0, 12);
		Page<MovieDTO> moviePage = service.findAll(title, pageRequest);
		Assertions.assertNotNull(moviePage);
		Assertions.assertTrue(moviePage.hasContent());
		Assertions.assertEquals(moviePage.getSize(), 1);
	}
	
	@Test
	public void findByIdShouldReturnMovieDTOWhenIdExists() {
		MovieDTO movieDTO = service.findById(existId);
		Assertions.assertNotNull(movieDTO);
		Assertions.assertEquals(existId, movieDTO.getId());
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(nonExistId));
	}
	
	@Test
	public void insertShouldReturnMovieDTO() {
		MovieDTO movie = service.insert(movieDTO);
		Assertions.assertNotNull(movie);
		Assertions.assertEquals(movieDTO.getTitle(), movie.getTitle());
	}
	
	@Test
	public void updateShouldReturnMovieDTOWhenIdExists() {
		MovieDTO movie = service.update(existId, movieDTO);
		Assertions.assertNotNull(movie);
		Assertions.assertEquals(movieDTO.getTitle(), movie.getTitle());
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> service.update(nonExistId, movieDTO));
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> service.delete(existId));
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistId));
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
		Assertions.assertThrows(DatabaseException.class, () -> service.delete(dependentId));
	}
}
