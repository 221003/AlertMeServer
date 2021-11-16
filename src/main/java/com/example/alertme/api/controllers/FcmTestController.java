package com.example.alertme.api.controllers;

import com.example.alertme.api.responses.ErrorResponse;
import com.example.alertme.api.responses.Response;
import com.example.alertme.api.responses.SuccessResponse;
import com.example.alertme.fcm.FcmService;
import com.example.alertme.fcm.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fcm")
public class FcmTestController {

    private FcmService fcmService;

    private Logger logger = LoggerFactory.getLogger(FcmTestController.class);

    public FcmTestController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/topic")
    public ResponseEntity<Response> sendNotification(@RequestBody PushNotificationRequest request) {
        try {

            fcmService.sendMessageWithoutData(request);
            return ResponseEntity.ok(new SuccessResponse(null));

        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), 100));
        }
    }

    @PostMapping("/token")
    public ResponseEntity<Response> sendTokenNotification(@RequestBody PushNotificationRequest request) {
        try {

            fcmService.sendMessageToToken(request);
            return ResponseEntity.ok(new SuccessResponse(null));

        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), 100));
        }
    }

    @PostMapping("/data")
    public ResponseEntity<Response> sendDataNotification(@RequestBody PushNotificationRequest request) {
        try {

            fcmService.sendMessage(getSamplePayloadData(), request);
            return ResponseEntity.ok(new SuccessResponse(null));

        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), 100));
        }
    }

    private Map<String, String> getSamplePayloadData() {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("messageId", "msgid");
        pushData.put("text", "txt");
        pushData.put("user", "pankaj singh");
        return pushData;
    }

}
