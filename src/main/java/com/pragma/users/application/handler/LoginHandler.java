package com.pragma.users.application.handler;

import com.pragma.users.application.dto.LoginDTORequest;
import com.pragma.users.application.dto.LoginDTOResponse;
import com.pragma.users.domain.api.IAuthenticationServicePort;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoginHandler implements ILoginHandler {
    private final IAuthenticationServicePort iAuthenticationServicePort;
    private final IUserServicePort iUserServicePort;

    @Override
    public LoginDTOResponse authenticationUser(LoginDTORequest loginDTORequest) {
        log.info(ConstantsErrorMessages.START_AUTHENTICATION_FLOW);
        String token = iAuthenticationServicePort.login(loginDTORequest.getEmail(),loginDTORequest.getPassword());
        String nameRol = iUserServicePort.getUserByEmail(loginDTORequest.getEmail()).getRol().getNameRol();
        return new LoginDTOResponse(token,nameRol);
    }
}
