package com.pragma.users.infrastructure.input.rest;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.handler.IUserHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/create-owner")
public class UserRestController {
    private final IUserHandler userHandler;

    public UserRestController(IUserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @PostMapping
    public ResponseEntity<Void> createOwnerUser(@RequestBody UserDTORequest userDTORequest,
                                                @RequestHeader String emailCreator){
        userHandler.saveUserDTOOwner(userDTORequest,emailCreator);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
