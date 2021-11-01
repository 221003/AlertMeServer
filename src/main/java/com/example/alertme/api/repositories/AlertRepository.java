package com.example.alertme.api.repositories;

import com.example.alertme.api.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
