package com.pragma.users.application.handler;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;

import java.util.List;

public interface IUserHandler {
    void saveUserDTOOwner(UserDTORequest userDTORq, String emailCreator);

    List<UserDTOResponse> getAllUserDTO();

    UserDTOResponse getUserDTO(String email);
}
