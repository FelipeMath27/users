package com.pragma.users.domain.usecase;

import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.spi.IRolPersistencePort;

public class UseCaseRol implements IRolServicePort {

    private final IRolPersistencePort rolPersistencePort;

    public UseCaseRol(IRolPersistencePort rolPersistencePort){
        this.rolPersistencePort = rolPersistencePort;
    }

    @Override
    public void saveRol(Rol rol)    {
        rolPersistencePort.saveRol(rol);
    }

    @Override
    public Rol getRolByName(String nameRol) {
        return rolPersistencePort.getRolByName(nameRol);
    }

    @Override
    public Rol getRolById(Long idRol) {
        return rolPersistencePort.getRolById(idRol);
    }


}