package com.example.alertme.api.repositories;

import com.example.alertme.api.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUser_id(Long user_id);
    List<Alert> findByNumberOfVotesLessThanEqual(int numberOfVotes);

}
