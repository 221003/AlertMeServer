package com.example.alertme.api.controllers;

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

    @PostMapping("")
    ResponseEntity<EntityModel<User>> newUser(@RequestBody User newUser) {

        newUser.setPassword_hash(passwordEncoder.encode(newUser.getPassword_hash()));
        User customer = repository.save(newUser);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class)).toUri()) //
                .body(assembler.toModel(customer));
    }


    @PostMapping("/login")
    ResponseEntity<EntityModel<User>> login(@RequestBody LoginForm loginForm) {
        User customer = repository.findOneByLoginOrEmail(loginForm.getLogin(), loginForm.getLogin());

        if(!passwordEncoder.matches(loginForm.getPassword(), customer.getPassword_hash())) {
            return ResponseEntity.badRequest().body(null);
        }

        EntityModel<User> entityModel = assembler.toModel(customer);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
