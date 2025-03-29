package com.pragma.users.domain.spi;

import com.pragma.users.domain.model.Rol;

import java.util.List;

public interface IRolPersistencePort {
    void saveRol(Rol rol);

    Rol getRol(String name_rol);

}
