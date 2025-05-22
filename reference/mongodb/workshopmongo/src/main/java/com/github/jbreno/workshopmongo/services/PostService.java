package com.github.jbreno.workshopmongo.services;

import com.github.jbreno.workshopmongo.models.dto.PostDTO;
import com.github.jbreno.workshopmongo.models.dto.PostDTO;
import com.github.jbreno.workshopmongo.models.entities.Post;
import com.github.jbreno.workshopmongo.repositories.PostRepository;
import com.github.jbreno.workshopmongo.repositories.PostRepository;
import com.github.jbreno.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private Post getEntityById(String id) {
        return postRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Usuário não encontrado");
        });
    }
}
