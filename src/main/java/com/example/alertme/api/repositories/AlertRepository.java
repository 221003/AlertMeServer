package com.example.alertme.api.repositories;

import com.example.alertme.api.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
