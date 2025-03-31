package com.pragma.users.application.dto;

import com.pragma.users.domain.model.TypeDocumentEnum;

import java.time.LocalDate;

public class UserDTORequest {
    private String nameUser;
    private String lastNameUser;
    private TypeDocumentEnum typeDocumentUser;
    private String documentUser;
    private String phoneNumberUser;
    private LocalDate dateBirthUser;
    private String email;
    private String password;
    private String nameRol;

    public UserDTORequest(String nameUser, String lastNameUser, TypeDocumentEnum typeDocumentUser, String documentUser,
                          String phoneNumberUser, LocalDate dateBirthUser, String email, String password, String nameRol) {
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
        this.typeDocumentUser = typeDocumentUser;
        this.documentUser = documentUser;
        this.phoneNumberUser = phoneNumberUser;
        this.dateBirthUser = dateBirthUser;
        this.email = email;
        this.password = password;
        this.nameRol = nameRol;
    }

    public UserDTORequest() {
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

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    @Override
    public String toString() {
        return "UserDTORequest{" +
                "nameUser='" + nameUser + '\'' +
                ", lastNameUser='" + lastNameUser + '\'' +
                ", typeDocumentUser=" + typeDocumentUser +
                ", documentUser='" + documentUser + '\'' +
                ", phoneNumberUser='" + phoneNumberUser + '\'' +
                ", dateBirthUser=" + dateBirthUser +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nameRol='" + nameRol + '\'' +
                '}';
    }
}


