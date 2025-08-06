package com.pragma.users.infrastructure.input.rest;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        log.info("{}", userDTOResponse);
        return ResponseEntity.ok(userDTOResponse);
    }

    @GetMapping("/id/{idUser}")
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable Long idUser) {
        log.info(ConstantsErrorMessages.LISTENER_OK_CONTROLLER);
        UserDTOResponse userDTOResponse = userHandler.getUserDTOById(idUser);
        return ResponseEntity.ok(userDTOResponse);
    }

    @PostMapping("/create-employee")
    public ResponseEntity<UserDTOResponse> createEmployee(@Valid @RequestBody UserDTORequest userDTORequest) {
        log.info(ConstantsErrorMessages.LISTENER_OK_CONTROLLER);
        UserDTOResponse userDTOResponse = userHandler.saveEmployee(userDTORequest);
        return ResponseEntity.ok(userDTOResponse);
    }
}
