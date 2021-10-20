package com.example.alertme.api.controllers;

import com.example.alertme.api.exceptions.UserNotFoundException;
import com.example.alertme.api.models.User;
import com.example.alertme.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @GetMapping("")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> user = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                user,
                linkTo(methodOn(UserController.class).all()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<User>> one(@PathVariable Long id) {
        User customer = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        EntityModel<User> entityModel = assembler.toModel(customer);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping("")
    ResponseEntity<EntityModel<User>> newUser(@RequestBody User newUser) {

        newUser.setPassword_hash(passwordEncoder.encode(newUser.getPassword_hash()));
        User user = repository.save(newUser);

        EntityModel<User> entityModel = assembler.toModel(user);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
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
