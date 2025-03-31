package com.pragma.users.domain.spi;

import com.pragma.users.domain.model.Rol;

public interface IRolPersistencePort {
    void saveRol(Rol rol);

    Rol getRolByName(String nameRol);

}
