package com.github.jbreno.workshopmongo.services;

import com.github.jbreno.workshopmongo.models.dto.UserDTO;
import com.github.jbreno.workshopmongo.models.entities.User;
import com.github.jbreno.workshopmongo.repositories.UserRepository;
import com.github.jbreno.workshopmongo.services.exceptions.ResourceNotFoundException;
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

    public UserDTO findById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Usuário não encontrado");
        });

        return new UserDTO(user);
    }

    public UserDTO insert(UserDTO userDTO) {
        User user = new User();
        copyDtoToEntity(userDTO, user);
        user = userRepository.insert(user);
        return new UserDTO(user);
    }

    private static void copyDtoToEntity(UserDTO userDTO, User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
    }
}
