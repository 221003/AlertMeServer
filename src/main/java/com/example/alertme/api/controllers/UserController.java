package com.example.alertme.api.controllers;

import com.example.alertme.api.ErrorResponse;
import com.example.alertme.api.Response;
import com.example.alertme.api.SuccessResponse;
import com.example.alertme.api.exceptions.UserNotFoundException;
import com.example.alertme.api.exceptions.WrongPasswordException;
import com.example.alertme.api.models.User;
import com.example.alertme.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    ResponseEntity<Response> all() {
        try {
            List<User> users = repository.findAll();

            return ResponseEntity.ok(new SuccessResponse(users));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{id}")
    ResponseEntity<Response> one(@PathVariable Long id) {
        try {
            User user = repository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id.toString()));

            return ResponseEntity.ok(new SuccessResponse(user));
        } catch (UserNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("")
    ResponseEntity<Response> newUser(@RequestBody User newUser) {
        try {
            newUser.setPassword_hash(passwordEncoder.encode(newUser.getPassword_hash()));
            User user = repository.save(newUser);

            return ResponseEntity.ok(new SuccessResponse(user));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/login")
    ResponseEntity<Response> login(@RequestBody LoginForm loginForm) {
        try {
            User user = repository
                    .findOneByLoginOrEmail(loginForm.getLogin(), loginForm.getLogin())
                    .orElseThrow(() -> new UserNotFoundException(loginForm.getLogin()));

            if(!passwordEncoder.matches(loginForm.getPassword(), user.getPassword_hash())) {
                throw new WrongPasswordException();
            }

            return ResponseEntity.ok(new SuccessResponse(user));
        } catch (UserNotFoundException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (WrongPasswordException th) {
            return ResponseEntity.badRequest().body(new ErrorResponse(th.getMessage(), th.getErrorCode()));
        } catch (Exception th) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
