package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import com.devsuperior.dsmovie.tests.ScoreFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {
	
	@InjectMocks
	private ScoreService service;

	@Mock
	private ScoreRepository repository;

	@Mock
	private UserService userService;

	@Mock
	private MovieRepository movieRepository;

	private Long existId, nonExistId;
	private UserEntity user;
	private MovieEntity movie;
	private ScoreEntity score;
	private ScoreDTO scoreDTO, invalidScoreDTO;

	@BeforeEach
	public void setUp() throws Exception {
		existId = 1L;
		nonExistId = 2L;
		user = UserFactory.createUserEntity();
		movie = MovieFactory.createMovieEntity();
		score = ScoreFactory.createScoreEntity();
		movie.getScores().add(score);
		scoreDTO = ScoreFactory.createScoreDTO();
		invalidScoreDTO = new ScoreDTO(nonExistId, scoreDTO.getScore());

		Mockito.when(movieRepository.findById(existId)).thenReturn(Optional.of(movie));
		Mockito.when(movieRepository.findById(nonExistId)).thenReturn(Optional.empty());

		Mockito.when(repository.saveAndFlush(any())).thenReturn(score);

		Mockito.when(movieRepository.save(any())).thenReturn(movie);
	}
	
	@Test
	public void saveScoreShouldReturnMovieDTO() {
		Mockito.when(userService.authenticated()).thenReturn(user);
		MovieDTO movieDTO = service.saveScore(scoreDTO);
		Assertions.assertNotNull(movieDTO);
		Assertions.assertEquals(scoreDTO.getMovieId(), movieDTO.getId());
	}

	
	@Test
	public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {
		Mockito.when(userService.authenticated()).thenReturn(user);

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			MovieDTO movieDTO = service.saveScore(invalidScoreDTO);
		});
	}
}
