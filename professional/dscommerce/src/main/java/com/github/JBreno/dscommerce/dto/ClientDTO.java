package com.github.JBreno.dscommerce.dto;

import com.github.JBreno.dscommerce.entities.User;

public class ClientDTO {
    private Long id;
    private String name;

    public ClientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientDTO(User user) {
        id = user.getId();
        name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
