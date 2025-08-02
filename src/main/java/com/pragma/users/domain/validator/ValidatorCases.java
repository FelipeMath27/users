package com.pragma.users.domain.validator;

import com.pragma.users.domain.model.TypeDocumentEnum;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class ValidatorCases {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com(\\.co)?$";
    private static final String PHONE_REGEX = "^\\+57\\d{10}$";
    private static final String NUMERIC_DOCUMENT_REGEX = "^[0-9]+$";
    private static final String PASSPORT_REGEX = "^[A-Za-z0-9]+$";
    private static final int LEGAL_AGE = 18;


    private ValidatorCases() {
    }

    /** This method sanitized any string*/
    public static Optional<String> sanitize(String input) {
        return Optional.ofNullable(input).map(String::trim).
                filter(s -> !s.isEmpty());
    }

    /**Regex to validate the email format*/
    public static Optional<String> validateEmail(String email){
        return sanitize(email)
                .filter(e -> e.matches(EMAIL_REGEX));
    }

    /**Regex to validate the phone number format*/
    public static Optional<String> validatePhoneNumber(String phoneNumber){
        return sanitize(phoneNumber)
                .filter(e->e.matches(PHONE_REGEX));
    }

    /**Regex to validate document number format*/
    public static Optional<String> validateDocumentNumber(TypeDocumentEnum typeDocument,
                                                         String documentNumber){
        return Optional.ofNullable(typeDocument)
                .flatMap(t -> sanitize(documentNumber)
                .filter(doc -> switch (t){
                    case CC, CE, TI -> doc.matches(NUMERIC_DOCUMENT_REGEX);
                    case PS -> doc.matches(PASSPORT_REGEX);
                }));
    }

    /**Regex to validate if the user is adult*/
    public static Optional<LocalDate> validateIsAdult(LocalDate dateOfBirth){
        return Optional.ofNullable(dateOfBirth)
                .filter(date ->
                        Period.between(date, LocalDate.now()).getYears() >= LEGAL_AGE);
    }
}
