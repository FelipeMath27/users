package com.pragma.users.infrastructure.input.rest;

import com.pragma.users.application.dto.RolDTOResponse;
import com.pragma.users.application.handler.IRolHandler;
import com.pragma.users.domain.usecase.UseCaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/roles")
public class RolRestController {
    private final IRolHandler rolHandler;

    private static final Logger logger = LoggerFactory.getLogger(UseCaseUser.class);

    public RolRestController(IRolHandler rolHandler) {
        this.rolHandler = rolHandler;
    }

    @GetMapping("/{nameRol}")
    public ResponseEntity<RolDTOResponse> getRolByName(@PathVariable String nameRol) {
        logger.info("Petición para obtener rol con nombre rol: {}", nameRol);
        RolDTOResponse rolDTOResponse = rolHandler.getRolDTO(nameRol);
        logger.info("Respuesta del rol: {}", rolDTOResponse);
        return ResponseEntity.ok(rolDTOResponse);
    }

    @GetMapping("/id/{idRol}")
    public ResponseEntity<RolDTOResponse> getRolById(@PathVariable Long idRol){
        RolDTOResponse rolDTOResponse = rolHandler.getRolDTOId(idRol);
        return ResponseEntity.ok(rolDTOResponse);
    }
}
