package com.pragma.talentpool.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Rol {
    private UUID id_rol;
    private String name_rol;
    private String description_rol;

    public Rol(UUID id_rol, String name_rol, String description_rol) {
        this.id_rol = id_rol;
        this.name_rol = name_rol;
        this.description_rol = description_rol;
    }
}


