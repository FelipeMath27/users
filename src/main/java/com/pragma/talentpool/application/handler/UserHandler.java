package com.pragma.talentpool.application.handler;

import com.pragma.talentpool.application.dto.UserDTORequest;
import com.pragma.talentpool.application.dto.UserDTOResponse;
import com.pragma.talentpool.application.mapper.RolRequestMapper;
import com.pragma.talentpool.application.mapper.RolResponseMapper;
import com.pragma.talentpool.application.mapper.UserRequestMapper;
import com.pragma.talentpool.application.mapper.UserResponseMapper;
import com.pragma.talentpool.domain.api.IRolServicePort;
import com.pragma.talentpool.domain.api.IUserServicePort;

import com.pragma.talentpool.domain.model.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler{
    private final IUserServicePort iUserServicePort;
    private final IRolServicePort iRolServicePort;
    private final RolRequestMapper rolRequestMapper;
    private final RolResponseMapper rolResponseMapper;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public void saveUserDTO(UserDTORequest userDTORq) {
    }

    @Override
    public List<UserDTOResponse> getAllUserDTO() {
        return List.of();
    }

    @Override
    public UserDTOResponse getUserDTO(String email) {
        return null;
    }

    @Override
    public void updateUserDTO(UserDTORequest userDTORq) {

    }

    @Override
    public void deleteUserDTO(String email) {

    }
}
