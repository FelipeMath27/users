package com.pragma.users.infrastructure.security;

import com.pragma.users.domain.utils.ConstantsErrorMessages;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {

    private  final PasswordEncoder passwordEncoder;

    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encryptPassword(String password){
        if(password == null || password.isBlank()){
            throw new IllegalArgumentException(ConstantsErrorMessages.PASSWORD_CANNOT_BE_EMPTY);
        }
        return passwordEncoder.encode(password);
    }

    public boolean verifyPassword(String rawPassword, String encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
//Implementar interfaz en vez de clase