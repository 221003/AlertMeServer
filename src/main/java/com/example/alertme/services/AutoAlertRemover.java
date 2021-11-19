package com.example.alertme.services;

import com.example.alertme.api.models.Alert;
import com.example.alertme.api.models.Vote;
import com.example.alertme.api.repositories.AlertRepository;
import com.example.alertme.api.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class AutoAlertRemover {

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private VoteRepository voteRepository;

    private static final Logger LOGGER = Logger.getLogger(AutoAlertRemover.class.getName());
    private static final int LOWER_LIMIT_VOTES = -10;
    private static final int EVERY_HOUR = 1000 * 3600;


    @Scheduled(fixedRate = EVERY_HOUR)
    public void removeAlertsBasedOnNegativeVotes() {
        List<Alert> alerts = alertRepository.findByNumberOfVotesLessThanEqual(LOWER_LIMIT_VOTES);
        for (Alert alert : alerts) {
            removeAllVotesAssignedToAlert(alert.getId());
            LOGGER.info("Auto removing an alert based on negative votes: " + alert.getNumber_of_votes());
            LOGGER.info("Removed alert details: " + alert.getShortDescription());
            alertRepository.delete(alert);
        }
    }

    private void removeAllVotesAssignedToAlert(long alertId) {
        List<Vote> votes = voteRepository.findByAlertId(alertId);
        for (Vote vote : votes) {
            LOGGER.info("Auto removing an vote, assigned to alert with negative number of votes");
            LOGGER.info("Removed vote details: " + vote.getShortDescription());
            voteRepository.delete(vote);
        }
        votes.forEach(vote -> voteRepository.delete(vote));
    }
}