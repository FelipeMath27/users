package com.pragma.users.application.handler;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;

public interface IUserHandler {
    void saveUserDTOOwner(UserDTORequest userDTORq);

    UserDTOResponse getUserDTO(String email);

    UserDTOResponse getUserDTOById(Long idUser);

    UserDTOResponse saveEmployee(UserDTORequest userDTORequest);
}
