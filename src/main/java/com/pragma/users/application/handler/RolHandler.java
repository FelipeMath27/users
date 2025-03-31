package com.pragma.users.application.handler;

import com.pragma.users.application.dto.RolDTORequest;
import com.pragma.users.application.dto.RolDTOResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolHandler implements IRolHandler{
    @Override
    public void saveRolDto(RolDTORequest rolDTORequest) {
    }

    @Override
    public RolDTOResponse getRolDTO(String nameRol) {
        return null;
    }
}
