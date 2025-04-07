package com.pragma.users.infrastructure.input.rest;

import com.pragma.users.application.dto.RolDTOResponse;
import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.application.handler.IRolHandler;
import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.domain.usecase.UseCaseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserRestController {
    private final IUserHandler userHandler;

    private static final Logger logger = LoggerFactory.getLogger(UseCaseUser.class);

    @PostMapping("/create-owner")
    public ResponseEntity<Void> createOwnerUser(@RequestBody UserDTORequest userDTORequest,
                                                @RequestHeader String emailCreator){
        userHandler.saveUserDTOOwner(userDTORequest,emailCreator);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTOResponse> getUserByEmail(@PathVariable String email) {
        logger.info("Petición para obtener usuario con email: {}", email);
        UserDTOResponse userDTOResponse = userHandler.getUserDTO(email);
        logger.info("Respuesta del usuario: {}", userDTOResponse);
        return ResponseEntity.ok(userDTOResponse);
    }

    @GetMapping("/id/{idUser}")
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable Long idUser) {
        UserDTOResponse userDTOResponse = userHandler.getUserDTOById(idUser);
        return ResponseEntity.ok(userDTOResponse);
    }


}
