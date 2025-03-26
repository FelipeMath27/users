package com.pragma.talentpool.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Rol {
    private UUID idRol;
    private String nameRol;
    private String descriptionRol;

    public Rol(UUID idRol, String nameRol, String descriptionRol) {
        this.idRol = idRol;
        this.nameRol = nameRol;
        this.descriptionRol = descriptionRol;
    }
}


