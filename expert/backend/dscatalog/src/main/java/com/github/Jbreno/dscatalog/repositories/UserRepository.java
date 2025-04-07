package com.github.Jbreno.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.Jbreno.dscatalog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
