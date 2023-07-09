package com.srishti.authservice.repository;

import com.srishti.authservice.model.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserCredential, String> {
    Optional<UserCredential> findByUsername(String username);
}
