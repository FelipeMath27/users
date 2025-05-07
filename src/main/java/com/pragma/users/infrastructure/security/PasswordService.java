package com.pragma.users.infrastructure.security;

import com.pragma.users.domain.utils.ConstantsErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordService implements IPasswordService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encryptPassword(String password){
        if(password == null || password.isBlank()){
            throw new IllegalArgumentException(ConstantsErrorMessages.PASSWORD_CANNOT_BE_EMPTY);
        }
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean verifyPassword(String rawPassword, String encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
