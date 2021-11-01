package com.example.alertme.api.controllers;

import com.example.alertme.api.exceptions.AlertTypeNotFoundException;
import com.example.alertme.api.repositories.AlertTypeRepository;
import com.example.alertme.api.repositories.UserRepository;
import com.example.alertme.api.requests.NewAlertRequestBody;
import com.example.alertme.api.responses.ErrorResponse;
import com.example.alertme.api.responses.Response;
import com.example.alertme.api.responses.SuccessResponse;
import com.example.alertme.api.exceptions.AlertNotFoundException;
import com.example.alertme.api.exceptions.UserNotFoundException;
import com.example.alertme.api.models.Alert;
import com.example.alertme.api.repositories.AlertRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertRepository repository;
    private final UserRepository userRepository;
    private final AlertTypeRepository alertTypeRepository;

    public AlertController(AlertRepository repository, UserRepository userRepository, AlertTypeRepository alertTypeRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.alertTypeRepository = alertTypeRepository;
    }

    @GetMapping("")
    ResponseEntity<Response> all() {
        try {
            List<Alert> alerts = repository.findAll();

            return ResponseEntity.ok(new SuccessResponse(alerts));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{id}")
    ResponseEntity<Response> one(@PathVariable Long id) {
        try {
            Alert alert = repository.findById(id)
                    .orElseThrow(() -> new AlertNotFoundException(id.toString()));

            return ResponseEntity.ok(new SuccessResponse(alert));
        } catch (AlertNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Response> update(@RequestBody NewAlertRequestBody newAlert, @PathVariable Long id) {
        try {
            Alert updatedAlert = repository.findById(id)
                    .map(alert -> {
                        alert.setFromRequestBody(userRepository, alertTypeRepository, newAlert);
                        return repository.save(alert);
                    })
                    .orElseThrow(() -> new AlertNotFoundException(id.toString()));

            return ResponseEntity.ok(new SuccessResponse(updatedAlert));
        } catch (AlertTypeNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (UserNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (AlertNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("")
    ResponseEntity<Response> newAlert(@RequestBody NewAlertRequestBody newAlert) {
        try {
            Alert alert = new Alert();
            alert.setFromRequestBody(userRepository, alertTypeRepository, newAlert);
            repository.save(alert);

            return ResponseEntity.ok(new SuccessResponse(alert));
        } catch (AlertTypeNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (UserNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
