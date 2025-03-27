package com.pragma.users.domain.api;

import com.pragma.users.domain.model.Rol;

import java.util.List;

public interface IRolServicePort {
    void saveRol(Rol rol);

    List<Rol> getAllRoles();

    Rol getRol(String name_rol);
}
