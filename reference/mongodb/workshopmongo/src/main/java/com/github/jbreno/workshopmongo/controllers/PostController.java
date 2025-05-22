package com.github.jbreno.workshopmongo.controllers;

import com.github.jbreno.workshopmongo.models.dto.PostDTO;
import com.github.jbreno.workshopmongo.models.dto.PostDTO;
import com.github.jbreno.workshopmongo.services.PostService;
import com.github.jbreno.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        PostDTO postDTO = postService.findById(id);
        return ResponseEntity.ok(postDTO);
    }

    @GetMapping(path = "/titleSearch")
    public ResponseEntity<List<PostDTO>> findByTitle(
            @RequestParam(
                    value = "text",
                    defaultValue = "")
            String text) {
        return ResponseEntity.ok(postService.findByTitle(text));
    }
}
