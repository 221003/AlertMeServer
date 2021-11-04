package com.example.alertme.api.repositories;

import com.example.alertme.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);
}
