package com.pragma.users.application.dto;

import com.pragma.users.domain.model.TypeDocumentEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTORequest {
    @NotBlank
    private String nameUser;
    @NotBlank
    private String lastNameUser;
    @NotNull
    private TypeDocumentEnum typeDocumentUser;
    @NotBlank
    private String documentUser;
    @NotBlank
    private String phoneNumberUser;
    @NotNull
    private LocalDate dateBirthUser;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nameRol;
}


