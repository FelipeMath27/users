package com.pragma.users.domain.model;

import lombok.Getter;

@Getter
public enum TypeDocumentEnum {
    CC("CC"),  // Cédula de Ciudadanía
    CE("CE"),  // Cédula de Extranjería
    TI("TI"),  // Tarjeta de Identidad
    NIT("NIT"), // Número de Identificación Tributaria
    PS("PS");  // Pasaporte

    private final String code;

    TypeDocumentEnum(String code) {
        this.code = code;
    }
}