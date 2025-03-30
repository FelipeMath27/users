package com.pragma.users.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Rol {
    private Long idRol;
    private String nameRol;
    private String descriptionRol;

    public Rol(Long idRol, String nameRol, String descriptionRol) {
        this.idRol = idRol;
        this.nameRol = nameRol;
        this.descriptionRol = descriptionRol;
    }
}


