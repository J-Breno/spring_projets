package com.github.jbreno.workshopmongo.repositories;

import com.github.jbreno.workshopmongo.models.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
