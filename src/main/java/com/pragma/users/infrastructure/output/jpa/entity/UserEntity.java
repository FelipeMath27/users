    package com.pragma.users.infrastructure.output.jpa.entity;

    import  com.pragma.users.domain.model.TypeDocumentEnum;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDate;

    @Entity
    @Table(name = "PRG_TBL_USERS")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idUser")
        private Long idUser;

        @NotBlank(message = "Name cannot be blank")
        @Column(name = "nameUser")
        private String nameUser;

        @NotBlank(message = "Last Name cannot be blank")
        @Column(name = "lastNameUser")
        private String lastNameUser;

        @NotNull(message = "Type Document is required")
        @Enumerated(EnumType.STRING)
        @Column(name = "typeDocumentUser")
        private TypeDocumentEnum typeDocumentUser;

        @NotBlank(message = "User number document is required")
        @Column(name = "documentUser")
        private String documentUser;

        @NotBlank(message = "User number phone is required")
        @Column(name = "phoneNumberUser")
        private String phoneNumberUser;

        @NotNull(message = "Type Document is required")
        @Column(name = "dateBirthUser")
        private LocalDate dateBirthUser;

        @NotBlank(message = "User email is required")
        @Column(name = "email")
        private String email;

        @NotBlank(message = "To create the user the password is required")
        @Column(name = "password")
        private String password;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "idRol")
        private RolEntity rolEntity;
    }
