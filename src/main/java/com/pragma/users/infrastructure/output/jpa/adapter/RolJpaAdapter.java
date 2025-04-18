package com.pragma.users.infrastructure.output.jpa.adapter;

import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.spi.IRolPersistencePort;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import com.pragma.users.infrastructure.output.jpa.entity.RolEntity;
import com.pragma.users.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.pragma.users.infrastructure.output.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolJpaAdapter implements IRolPersistencePort {
    private final IRolRepository iRolRepository;
    private final RolEntityMapper rolEntityMapper;

    private static final Logger logger = LoggerFactory.getLogger(RolJpaAdapter.class);

    @Override
    public void saveRol(Rol rol) {
        RolEntity rolEntity = rolEntityMapper.toRolEntity(rol);
        rolEntityMapper.toRol(iRolRepository.save(rolEntity));
    }

    @Override
    public Rol getRolByName(String nameRol) {
        log.info("Buscando rol con nombre: {}", nameRol);
        Optional<RolEntity> rolEntityOptional = iRolRepository.findByNameRol(nameRol);
        rolEntityOptional.ifPresent(entity -> log.info("Rol encontrado: {}", entity));
        return rolEntityOptional.map(rolEntityMapper::toRol)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantsErrorMessages.ROL_NOT_FOUND));
    }

    @Override
    public Rol getRolById(Long idRol) {
        Optional<RolEntity> rolEntityOptional = iRolRepository.findByIdRol(idRol);
        return rolEntityOptional.map(rolEntityMapper::toRol)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantsErrorMessages.ROL_NOT_FOUND));
    }
}
