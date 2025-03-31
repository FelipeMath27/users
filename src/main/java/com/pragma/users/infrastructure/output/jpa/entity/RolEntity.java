package com.pragma.users.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "PRG_TBL_ROLES")

public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRol;

    @NotBlank(message = "Name rol is required")
    private String nameRol;

    @NotBlank(message = "Description rol is required")
    private String descriptionRol;

    public RolEntity() {
    }

    public RolEntity(Long idRol, String nameRol, String descriptionRol) {
        this.idRol = idRol;
        this.nameRol = nameRol;
        this.descriptionRol = descriptionRol;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
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
}
