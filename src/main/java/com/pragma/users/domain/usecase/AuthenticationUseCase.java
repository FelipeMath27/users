package com.pragma.users.domain.usecase;

import com.pragma.users.domain.api.IAuthenticationServicePort;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import com.pragma.users.domain.validator.ValidatorCases;
import com.pragma.users.infrastructure.exceptions.CustomException;
import com.pragma.users.infrastructure.security.IJwtTokenProvider;
import com.pragma.users.infrastructure.security.IPasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IPasswordService iPasswordService;
    private final IJwtTokenProvider iJwtTokenProvider;
    private final IUserPersistencePort iUserPersistencePort;

    @Override
    public String login(String email, String password) {
        log.info(ConstantsErrorMessages.START_AUTHENTICATION_FLOW);
        ValidatorCases.validateEmail(email)
                .orElseThrow(()-> new CustomException(ConstantsErrorMessages.INVALID_EMAIL_FORMAT));
      User user = Optional.ofNullable(iUserPersistencePort.getUserByEmail(email))
              .orElseThrow(()->{
                  log.error(ConstantsErrorMessages.USER_NOT_FOUND);
                  return new CustomException(ConstantsErrorMessages.USER_NOT_FOUND);
              });
        if (!iPasswordService.verifyPassword(password, user.getPassword())) {
            log.error(ConstantsErrorMessages.ERROR_TO_VERIFY_PASSWORD);
            throw new CustomException(ConstantsErrorMessages.ERROR_TO_VERIFY_PASSWORD);
        }
        return iJwtTokenProvider.generateToken(user);
    }
}
