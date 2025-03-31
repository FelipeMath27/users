    package com.pragma.users.infrastructure.output.jpa.entity;

    import com.pragma.users.domain.model.TypeDocumentEnum;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;

    import java.time.LocalDate;

    @Entity
    @Table(name = "PRG_TBL_USERS")
    public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long idUser;

        @NotBlank(message = "Name cannot be blank")
        private String nameUser;

        @NotBlank(message = "Last Name cannot be blank")
        private String lastNameUser;

        @NotNull(message = "Type Document is required")
        @Enumerated(EnumType.STRING)
        private TypeDocumentEnum typeDocumentUser;

        @NotBlank(message = "User number document is required")
        private String documentUser;

        @NotBlank(message = "User number phone is required")
        private String phoneNumberUser;

        @NotNull(message = "Type Document is required")
        private LocalDate dateBirthUser;

        @NotBlank(message = "User email is required")
        private String email;

        @NotBlank(message = "To create the user the password is required")
        private String password;

        @ManyToOne
        @JoinColumn(name = "idRol")
        private RolEntity rolEntity;

        public UserEntity() {
        }

        public UserEntity(Long idUser, String nameUser, String lastNameUser, TypeDocumentEnum typeDocumentUser, String documentUser,
                          String phoneNumberUser, LocalDate dateBirthUser, String email, String password, RolEntity rolEntity) {
            this.idUser = idUser;
            this.nameUser = nameUser;
            this.lastNameUser = lastNameUser;
            this.typeDocumentUser = typeDocumentUser;
            this.documentUser = documentUser;
            this.phoneNumberUser = phoneNumberUser;
            this.dateBirthUser = dateBirthUser;
            this.email = email;
            this.password = password;
            this.rolEntity = rolEntity;
        }

        public Long getIdUser() {
            return idUser;
        }

        public void setIdUser(Long idUser) {
            this.idUser = idUser;
        }

        public String getNameUser() {
            return nameUser;
        }

        public void setNameUser(String nameUser) {
            this.nameUser = nameUser;
        }

        public String getLastNameUser() {
            return lastNameUser;
        }

        public void setLastNameUser(String lastNameUser) {
            this.lastNameUser = lastNameUser;
        }

        public TypeDocumentEnum getTypeDocumentUser() {
            return typeDocumentUser;
        }

        public void setTypeDocumentUser(TypeDocumentEnum typeDocumentUser) {
            this.typeDocumentUser = typeDocumentUser;
        }

        public String getDocumentUser() {
            return documentUser;
        }

        public void setDocumentUser(String documentUser) {
            this.documentUser = documentUser;
        }

        public String getPhoneNumberUser() {
            return phoneNumberUser;
        }

        public void setPhoneNumberUser(String phoneNumberUser) {
            this.phoneNumberUser = phoneNumberUser;
        }

        public LocalDate getDateBirthUser() {
            return dateBirthUser;
        }

        public void setDateBirthUser(LocalDate dateBirthUser) {
            this.dateBirthUser = dateBirthUser;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public RolEntity getRolEntity() {
            return rolEntity;
        }

        public void setRolEntity(RolEntity rolEntity) {
            this.rolEntity = rolEntity;
        }
    }
