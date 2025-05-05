package com.pragma.users.infrastructure.input.rest;

import com.pragma.users.application.dto.LoginDTORequest;
import com.pragma.users.application.dto.LoginDTOResponse;
import com.pragma.users.application.handler.ILoginHandler;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginRestController {
    private final ILoginHandler iLoginHandler;

    @PostMapping("/login")
    public ResponseEntity<LoginDTOResponse> login(
            @RequestBody LoginDTORequest loginDTORequest){
        log.info(ConstantsErrorMessages.LISTENER_OK_CONTROLLER);
        return ResponseEntity.ok(iLoginHandler.authenticationUser(loginDTORequest));
    }
}
