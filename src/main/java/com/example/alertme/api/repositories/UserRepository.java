package com.example.alertme.api.repositories;

import com.example.alertme.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLoginOrEmail(String login, String email);
}
