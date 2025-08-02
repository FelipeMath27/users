package com.pragma.users.infrastructure.input.rest;

import com.pragma.users.application.dto.RolDTOResponse;
import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.application.handler.IRolHandler;
import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.domain.usecase.UseCaseUser;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Slf4j
public class UserRestController {
    private final IUserHandler userHandler;

    @PostMapping("/create-owner")
    public ResponseEntity<Void> createOwnerUser(@RequestBody UserDTORequest userDTORequest){
        log.info(ConstantsErrorMessages.LISTENER_OK_CONTROLLER);
        userHandler.saveUserDTOOwner(userDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTOResponse> getUserByEmail(@PathVariable String email) {
        log.info(ConstantsErrorMessages.LISTENER_OK_CONTROLLER);
        UserDTOResponse userDTOResponse = userHandler.getUserDTO(email);
        log.info("Respuesta del usuario: {}", userDTOResponse);
        return ResponseEntity.ok(userDTOResponse);
    }

    @GetMapping("/id/{idUser}")
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable Long idUser) {
        log.info(ConstantsErrorMessages.LISTENER_OK_CONTROLLER);
        UserDTOResponse userDTOResponse = userHandler.getUserDTOById(idUser);
        return ResponseEntity.ok(userDTOResponse);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<Void> createAdmin(@RequestBody UserDTORequest userDTORequest){
        log.info(ConstantsErrorMessages.LISTENER_OK_CONTROLLER);
        userHandler.saveGeneralUser(userDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/create-employee")
    public ResponseEntity<Void> createEmployee(@RequestBody UserDTORequest userDTORequest){
        log.info(ConstantsErrorMessages.LISTENER_OK_CONTROLLER);
        userHandler.saveGeneralUser(userDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
