package com.example.alertme.api.controllers;

import com.example.alertme.api.requests.NewAlertTypeRequestBody;
import com.example.alertme.api.responses.ErrorResponse;
import com.example.alertme.api.responses.Response;
import com.example.alertme.api.responses.SuccessResponse;
import com.example.alertme.api.exceptions.AlertTypeNotFoundException;
import com.example.alertme.api.models.AlertType;
import com.example.alertme.api.repositories.AlertTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alert-types")
public class AlertTypeController {

    private final AlertTypeRepository repository;

    public AlertTypeController(AlertTypeRepository repository) {
        this.repository = repository;
    }


    @GetMapping("")
    ResponseEntity<Response> all() {
        try {
            List<AlertType> alertTypes = repository.findAll();

            return ResponseEntity.ok(new SuccessResponse(alertTypes));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{id}")
    ResponseEntity<Response> one(@PathVariable Long id) {
        try {
            AlertType alertType = repository.findById(id)
                    .orElseThrow(() -> new AlertTypeNotFoundException(id.toString()));

            return ResponseEntity.ok(new SuccessResponse(alertType));
        } catch (AlertTypeNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("")
    ResponseEntity<Response> newAlertType(@RequestBody NewAlertTypeRequestBody newAlertType) {
        try {
            AlertType alertType = new AlertType();
            alertType.setFromRequestBody(newAlertType);

            repository.save(alertType);

            return ResponseEntity.ok(new SuccessResponse(alertType));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("/{id}")
    ResponseEntity<Response> update(@RequestBody NewAlertTypeRequestBody newAlertType, @PathVariable Long id) {
        try {
            AlertType updatedAlertType = repository.findById(id)
                    .map(alertType -> {
                        alertType.setFromRequestBody(newAlertType);
                        return repository.save(alertType);
                    })
                    .orElseThrow(() -> new AlertTypeNotFoundException(id.toString()));

            return ResponseEntity.ok(new SuccessResponse(updatedAlertType));
        } catch (AlertTypeNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
