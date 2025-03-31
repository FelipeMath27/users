package com.pragma.users.domain.api;

import com.pragma.users.domain.model.Rol;

public interface IRolServicePort {
    void saveRol(Rol rol);

    Rol getRolByName(String nameRol);
}
