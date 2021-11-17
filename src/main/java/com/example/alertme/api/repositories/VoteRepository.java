package com.example.alertme.api.repositories;

import com.example.alertme.api.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByAlertIdAndUserId(Long alertId, Long userId);

}
