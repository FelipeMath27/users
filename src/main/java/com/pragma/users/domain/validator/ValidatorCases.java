package com.pragma.users.domain.validator;

import com.pragma.users.domain.model.TypeDocumentEnum;

import java.time.LocalDate;
import java.time.Period;

public class ValidatorCases {
    private ValidatorCases() {
    }

    public static String sanitizeString(String input) {
        return input == null ? null : input.trim();
    }

    //Constantes para informar que hace el validador...
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
        };
    }

    public static boolean isAdult(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
        }
}
