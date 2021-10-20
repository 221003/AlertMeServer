package com.example.alertme.api.controllers;

import com.example.alertme.api.exceptions.UserNotFoundException;
import com.example.alertme.api.models.User;
import com.example.alertme.api.models.UserModelAssembler;
import com.example.alertme.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repository;
    private final UserModelAssembler assembler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("")
    ResponseEntity<EntityModel<User>> newUser(@RequestBody User newUser) {

        newUser.setPassword_hash(passwordEncoder.encode(newUser.getPassword_hash()));
        User user = repository.save(newUser);

        EntityModel<User> entityModel = assembler.toModel(user);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(assembler.toModel(user));
    }


    @PostMapping("/login")
    ResponseEntity<EntityModel<User>> login(@RequestBody LoginForm loginForm) {
        User user = repository
                .findOneByLoginOrEmail(loginForm.getLogin(), loginForm.getLogin())
                .orElseThrow(() -> new UserNotFoundException(loginForm.getLogin()));

        if(!passwordEncoder.matches(loginForm.getPassword(), user.getPassword_hash())) {
            return ResponseEntity.badRequest().body(null);
        }

        EntityModel<User> entityModel = assembler.toModel(user);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
