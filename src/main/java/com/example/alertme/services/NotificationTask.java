package com.example.alertme.services;

import com.example.alertme.api.models.Alert;
import com.example.alertme.api.models.User;
import com.example.alertme.api.repositories.AlertRepository;
import com.example.alertme.api.repositories.UserRepository;
import com.example.alertme.fcm.FcmService;
import com.example.alertme.fcm.PushNotificationRequest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@EnableAsync
public class NotificationTask {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final FcmService fcmService;

    public NotificationTask(UserRepository userRepository, AlertRepository alertRepository, FcmService fcmService) {
        this.userRepository = userRepository;
        this.alertRepository = alertRepository;
        this.fcmService = fcmService;
    }

    @Scheduled(fixedRate = 900000)
    public void scheduleNotificationTask() throws ExecutionException, InterruptedException {
        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);

        List<Alert> alerts = alertRepository.findAll();
        List<User> users = userRepository.findByTokenIsNotNull();

        for (User user: users) {
            double userLatitude = 51.759;
            double userLongitude = 19.457;
            int alertCounter = 0;

            for (Alert alert: alerts) {

                double dist = distance(userLatitude, userLongitude, alert.getLatitude(), alert.getLongitude(), "K");
                System.out.println(dist);

                if (dist <= 10) {
                    alertCounter++;
                }
            }


            PushNotificationRequest request = new PushNotificationRequest();
            request.setToken(user.getToken());
            request.setTitle(alertCounter + " alerty w twojej okolicy");
            request.setMessage("Zobacz nowe alerty w twojej okolicy");

            fcmService.sendMessageToToken(request);
        }
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

}
