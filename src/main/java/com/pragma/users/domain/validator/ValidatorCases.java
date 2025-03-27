package com.pragma.users.domain.validator;

import com.pragma.users.domain.model.TypeDocumentEnum;
import com.pragma.users.domain.model.User;

import java.time.LocalDate;
import java.time.Period;

public class ValidatorCases {
    private ValidatorCases() {
    }

    public static String sanitizeString(String input) {
        return input == null ? null : input.trim();
    }

    public static boolean isValidEmail(String email) {
        if(email == null) {
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPhone(String phone) {
        if(phone == null) {
            return false;
        }
        return phone.matches("^\\+?[0-9]{1,13}$");
    }

    public static boolean isValidDocument(TypeDocumentEnum type, String document) {
        if (document == null) {
            return false;
        }
        return switch (type) {
            case CC, CE, TI -> document.matches("^[0-9]+$");
            case PS -> document.matches("^[A-Za-z0-9]+$");
            default -> false;
        };
    }

    public static boolean hasRole(User user, String role ){
        return user.getRol() != null && role.equals(user.getRol().getNameRol());
    }

    public static boolean isAdult(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;  // O lanzar excepción si el campo es obligatorio
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }
}
