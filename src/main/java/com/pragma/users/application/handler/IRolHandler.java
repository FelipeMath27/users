package com.pragma.users.application.handler;

import com.pragma.users.application.dto.RolDTORequest;
import com.pragma.users.application.dto.RolDTOResponse;

public interface IRolHandler {
    void saveRolDto(RolDTORequest rolDTORequest);

    RolDTOResponse getRolDTO(String nameRol);
}
