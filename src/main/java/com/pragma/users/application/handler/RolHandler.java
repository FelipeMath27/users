package com.pragma.users.application.handler;

import com.pragma.users.application.dto.RolDTORequest;
import com.pragma.users.application.dto.RolDTOResponse;
import com.pragma.users.application.mapper.RolRequestMapper;
import com.pragma.users.application.mapper.RolResponseMapper;
import com.pragma.users.domain.api.IRolServicePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class RolHandler implements IRolHandler{
    private final IRolServicePort iRolServicePort;
    private final RolRequestMapper rolRequestMapper;
    private final RolResponseMapper rolResponseMapper;

    private static final Logger logger = LoggerFactory.getLogger(RolHandler.class);

    public RolHandler(IRolServicePort iRolServicePort, RolRequestMapper rolRequestMapper,
                      RolResponseMapper rolResponseMapper) {
        this.iRolServicePort = iRolServicePort;
        this.rolRequestMapper = rolRequestMapper;
        this.rolResponseMapper = rolResponseMapper;
    }

    @Override
    public void saveRolDto(RolDTORequest rolDTORequest) {
    }

    @Override
    public RolDTOResponse getRolDTO(String nameRol) {
        RolDTOResponse response = rolResponseMapper.toResponse(iRolServicePort.getRolByName(nameRol));
        logger.info("Respuesta del rol mapeado: {}", response);
        return response;
    }
}
