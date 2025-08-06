package com.pragma.users.application.handler;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.application.mapper.RolResponseMapper;
import com.pragma.users.application.mapper.UserRequestMapper;
import com.pragma.users.application.mapper.UserResponseMapper;
import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;

import com.pragma.users.domain.usecase.UseCaseUser;


import com.pragma.users.domain.utils.ConstantsErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserHandler implements IUserHandler{

    private static final Logger logger = LoggerFactory.getLogger(UseCaseUser.class);

    private final IUserServicePort iUserServicePort;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final IRolServicePort iRolServicePort;
    private final RolResponseMapper rolResponseMapper;

    @Override
    public void saveUserDTOOwner(UserDTORequest userDTORq) {
        log.info(ConstantsErrorMessages.START_TO_CREATE_OWNER + "{}",userDTORq.getNameRol());
        iUserServicePort.saveUserOwner(userRequestMapper.toUser(userDTORq));
    }

    @Override
    public UserDTOResponse getUserDTO(String email) {
        return userResponseMapper.toUserDtoResponse(iUserServicePort.getUserByEmail(email));
    }

    @Override
    public UserDTOResponse getUserDTOById(Long idUser) {
        return userResponseMapper.toUserDtoResponse(iUserServicePort.getUserById(idUser));
    }

    @Override
    public UserDTOResponse saveEmployee(UserDTORequest userDTORequest) {
        log.info(ConstantsErrorMessages.START_TO_CREATE_EMPLOYEE + "{}",userDTORequest.getNameUser());
        return userResponseMapper.toUserDtoResponse(iUserServicePort.saveEmployee(userRequestMapper.toUser(userDTORequest)));
    }
}
