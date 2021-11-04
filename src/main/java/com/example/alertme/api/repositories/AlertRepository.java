package com.example.alertme.api.repositories;

import com.example.alertme.api.models.Alert;
import com.example.alertme.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUser_id(Long user_id);
}
