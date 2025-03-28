package com.example.twitter.repository;

import com.example.twitter.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username); // verificar si un usuario ya existe
}
