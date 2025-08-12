package com.pragma.users.domain.spi;

import com.pragma.users.domain.model.Rol;

import java.util.Optional;

public interface IRolPersistencePort {
    void saveRol(Rol rol);

    Optional<Rol> findByName(String nameRol);

    Rol getRolById(Long idRol);
}
