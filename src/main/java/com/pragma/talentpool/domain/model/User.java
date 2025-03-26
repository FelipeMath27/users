package com.pragma.talentpool.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class User {
	
    private UUID id_user;
	private String name_user;
	private String lastName_user;
    private TypeDocumentEnum typeDocument_user;
    private String document_user;
	private String phoneNumber_user;
	private LocalDate dateBirth_user;
	private String email;
    private String password;  // Guardará la contraseña hasheada
    private Rol idRol;

    public User(UUID id_user, String name_user, String lastName_user, TypeDocumentEnum typeDocument_user,
                String document_user, String phoneNumber_user, LocalDate dateBirth_user, String email, String password,
                Rol idRol) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.lastName_user = lastName_user;
        this.typeDocument_user = typeDocument_user;
        this.document_user = document_user;
        this.phoneNumber_user = phoneNumber_user;
        this.dateBirth_user = dateBirth_user;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
    }
}