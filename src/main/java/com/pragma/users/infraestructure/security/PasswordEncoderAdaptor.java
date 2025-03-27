package com.pragma.users.infraestructure.security;

import com.pragma.users.infraestructure.exceptions.ConstantsErrorMessages;
import com.pragma.users.infraestructure.exceptions.CustomException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordEncoderAdaptor {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private PasswordEncoderAdaptor(){
    }

    public static String encryptPassword(String password){
        if(password == null || password.isBlank()){
            throw new CustomException(ConstantsErrorMessages.PASSWORD_CANNOT_BE_EMPTY);
        }
        return passwordEncoder.encode(password);
    }
}
