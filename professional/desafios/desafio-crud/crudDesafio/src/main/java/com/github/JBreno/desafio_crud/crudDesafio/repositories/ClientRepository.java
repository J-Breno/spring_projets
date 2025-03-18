package com.github.JBreno.desafio_crud.crudDesafio.repositories;

import com.github.JBreno.desafio_crud.crudDesafio.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
