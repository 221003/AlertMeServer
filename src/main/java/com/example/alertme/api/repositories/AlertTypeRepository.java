package com.example.alertme.api.repositories;

import com.example.alertme.api.models.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertTypeRepository extends JpaRepository<AlertType, Long> {
}
