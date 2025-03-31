package com.pragma.users.application.dto;

public class RolDTOResponse {
    private String name_rol;
    private String description_rol;

    public RolDTOResponse() {
    }

    public RolDTOResponse(String name_rol, String description_rol) {
        this.name_rol = name_rol;
        this.description_rol = description_rol;
    }

    public String getName_rol() {
        return name_rol;
    }

    public void setName_rol(String name_rol) {
        this.name_rol = name_rol;
    }

    public String getDescription_rol() {
        return description_rol;
    }

    public void setDescription_rol(String description_rol) {
        this.description_rol = description_rol;
    }

    @Override
    public String toString() {
        return "RolDTOResponse{" +
                "name_rol='" + name_rol + '\'' +
                ", description_rol='" + description_rol + '\'' +
                '}';
    }

}
