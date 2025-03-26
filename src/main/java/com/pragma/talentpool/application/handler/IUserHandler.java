package com.pragma.talentpool.application.handler;

import com.pragma.talentpool.application.dto.UserDTORequest;
import com.pragma.talentpool.application.dto.UserDTOResponse;

import java.util.List;

public interface IUserHandler {
    void saveUserDTO(UserDTORequest userDTORq);

    List<UserDTOResponse> getAllUserDTO();

    UserDTOResponse getUserDTO(String email);

    void updateUserDTO(UserDTORequest userDTORq);

    void deleteUserDTO(String email);
}
