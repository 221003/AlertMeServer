package com.example.alertme.api.controllers;

import com.example.alertme.api.models.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(com.example.alertme.api.controllers.UserController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}
