package com.pragma.users.infrastructure.input.rest;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/create-owner")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @PostMapping
    public ResponseEntity<Void> createOwnerUser(@RequestBody UserDTORequest userDTORequest,
                                                @RequestParam String emailCreator){
        userHandler.saveUserDTOOwner(userDTORequest,emailCreator);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
