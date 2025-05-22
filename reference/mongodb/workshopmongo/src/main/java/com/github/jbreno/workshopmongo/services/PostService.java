package com.github.jbreno.workshopmongo.services;

import com.github.jbreno.workshopmongo.models.dto.PostDTO;
import com.github.jbreno.workshopmongo.models.entities.Post;
import com.github.jbreno.workshopmongo.repositories.PostRepository;
import com.github.jbreno.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDTO findById(String id) {
        Post post = getEntityById(id);

        return new PostDTO(post);
    }

    public List<PostDTO> findByTitle(String title) {
        List<Post> list = postRepository.findByTitleContainingIgnoreCase(title);
        return list.stream().map(x -> new PostDTO(x)).toList();
    }

    public List<PostDTO> fullSearch(String title, String start, String end) {
        Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
        Instant endMoment = convertMoment(end, Instant.now());

        List<Post> list = postRepository.fullSearch(title, startMoment, endMoment);
        return list.stream().map(x -> new PostDTO(x)).toList();
    }

    private Instant convertMoment(String originalString, Instant alternative) {
        try {
            return Instant.parse(originalString);
        } catch (DateTimeParseException e) {
            return alternative;
        }
    }

    private Post getEntityById(String id) {
        return postRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Usuário não encontrado");
        });
    }
}
