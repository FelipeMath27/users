package com.pragma.users.infrastructure.output.jpa.entity;

import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeDocumentEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "PRG_TBL_USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;

    @NotBlank(message = "Name cannot be blank")
    private String nameUser;

    @NotBlank(message = "Last Name cannot be blank")
    private String lastNameUser;

    @NotBlank(message = "Type Document is required")
    private TypeDocumentEnum typeDocumentUser;

    @NotBlank(message = "User number document is required")
    private String documentUser;

    @NotBlank(message = "User number phone is required")
    private String phoneNumberUser;

    @NotBlank(message = "User birthday is required")
    private LocalDate dateBirthUser;

    @NotBlank(message = "User email is required")
    private String email;

    @NotBlank(message = "To create the user the password is required")
    private String password;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false)
    private RolEntity rolEntity;
}
