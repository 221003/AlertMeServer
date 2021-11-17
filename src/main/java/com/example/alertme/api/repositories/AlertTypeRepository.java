package com.example.alertme.api.repositories;

import com.example.alertme.api.models.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AlertTypeRepository extends JpaRepository<AlertType, Long> {
}
