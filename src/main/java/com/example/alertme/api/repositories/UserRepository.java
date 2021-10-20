package com.example.alertme.api.repositories;

import com.example.alertme.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLoginOrEmail(String login, String email);
}
