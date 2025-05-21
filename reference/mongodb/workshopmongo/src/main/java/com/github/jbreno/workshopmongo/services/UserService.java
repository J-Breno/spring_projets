package com.github.jbreno.workshopmongo.services;

import com.github.jbreno.workshopmongo.models.dto.UserDTO;
import com.github.jbreno.workshopmongo.models.entities.User;
import com.github.jbreno.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(x -> new UserDTO(x)).toList();
    }
}
