package com.example.alertme.api.controllers;


import com.example.alertme.api.exceptions.AlertNotFoundException;
import com.example.alertme.api.models.Alert;
import com.example.alertme.api.models.User;
import com.example.alertme.api.models.Vote;
import com.example.alertme.api.repositories.AlertRepository;
import com.example.alertme.api.repositories.UserRepository;
import com.example.alertme.api.repositories.VoteRepository;
import com.example.alertme.api.requests.VoteBody;
import com.example.alertme.api.responses.ErrorResponse;
import com.example.alertme.api.responses.Response;
import com.example.alertme.api.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlertRepository alertRepository;

    @GetMapping("api/votes")
    public ResponseEntity<Response> getVotes() {
        return ResponseEntity.ok(new SuccessResponse(voteRepository.findAll()));
    }

    @PostMapping("api/votes/find")
    public ResponseEntity<Response> findVote(@RequestBody VoteBody voteBody) {
        Optional<Vote> vote = voteRepository
                .findByAlertIdAndUserId(voteBody.getAlertId(), voteBody.getUserId())
                .stream().findFirst();
        if (vote.isPresent()) {
            return ResponseEntity.ok(new SuccessResponse(vote));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("api/votes")
    public ResponseEntity<Response> createVote(@RequestBody VoteBody voteBody) {
        if (!voteRepository.findByAlertIdAndUserId(voteBody.getAlertId(), voteBody.getUserId()).isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Vote already exists", Math.toIntExact(voteRepository.findByAlertIdAndUserId(voteBody.getAlertId(), voteBody.getUserId()).get(0).getId())));
        }

        Optional<Alert> alert = alertRepository.findById(voteBody.getAlertId());
        Optional<User> user = userRepository.findById(voteBody.getUserId());
        if (alert.isPresent() && user.isPresent()) {
            Vote vote = voteRepository.save(new Vote(alert.get(), user.get(), voteBody.isUpped()));
            return ResponseEntity.ok(new SuccessResponse(vote));
        } else if (alert.isEmpty())
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("api/votes/{id}")
    public ResponseEntity<Response> updateVote(@RequestBody VoteBody voteBody, @PathVariable Long id) {
        Optional<Vote> vote = voteRepository.findById(id);
        if (vote.isPresent()) {
            vote.get().setAlert(alertRepository.getById(voteBody.getAlertId()));
            vote.get().setUser(userRepository.getById(voteBody.getUserId()));
            vote.get().setUpped(voteBody.isUpped());
            Vote updatedVote = voteRepository.save(vote.get());
            return ResponseEntity.ok().body(new SuccessResponse(updatedVote));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("api/votes/{id}")
    public ResponseEntity<Response> deleteVote(@PathVariable Long id) {
        voteRepository.deleteById(id);
        return ResponseEntity.ok().body(null);
    }


}
