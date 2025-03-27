package com.pragma.users.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class User {
	
    private UUID idUser;
	private String nameUser;
	private String lastNameUser;
    private TypeDocumentEnum typeDocumentUser;
    private String documentUser;
	private String phoneNumberUser;
	private LocalDate dateBirthUser;
	private String email;
    private String password;  // Guardará la contraseña hasheada
    private Rol rol;

    public User(UUID idUser, String nameUser, String lastNameUser, TypeDocumentEnum typeDocumentUser, String documentUser,
                String phoneNumberUser, LocalDate dateBirthUser, String email, String password, Rol rol) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
        this.typeDocumentUser = typeDocumentUser;
        this.documentUser = documentUser;
        this.phoneNumberUser = phoneNumberUser;
        this.dateBirthUser = dateBirthUser;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
}