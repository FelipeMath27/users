package com.pragma.users.application.dto;

public class RolDTORequest {
    private String nameRol;
    private String descriptionRol;

    public RolDTORequest() {
    }

    public RolDTORequest(String nameRol, String descriptionRol) {
        this.nameRol = nameRol;
        this.descriptionRol = descriptionRol;
    }

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public String getDescriptionRol() {
        return descriptionRol;
    }

    public void setDescriptionRol(String descriptionRol) {
        this.descriptionRol = descriptionRol;
    }

    @Override
    public String toString() {
        return "RolDTORequest{" +
                "nameRol='" + nameRol + '\'' +
                ", descriptionRol='" + descriptionRol + '\'' +
                '}';
    }


}
