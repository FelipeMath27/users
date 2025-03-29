package com.pragma.users.application.handler;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.application.mapper.RolRequestMapper;
import com.pragma.users.application.mapper.RolResponseMapper;
import com.pragma.users.application.mapper.UserRequestMapper;
import com.pragma.users.application.mapper.UserResponseMapper;
import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;

import com.pragma.users.domain.model.User;
import com.pragma.users.domain.validator.ValidatorCases;
import com.pragma.users.infrastructure.exceptions.ConstantsErrorMessages;
import com.pragma.users.infrastructure.exceptions.CustomException;
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
    public void saveUserDTOOwner(UserDTORequest userDTORq, String emailCreator) {
        if(!ValidatorCases.isValidEmail(emailCreator) ||
                !ValidatorCases.isValidEmail(userDTORq.getEmail())){
            throw new CustomException(ConstantsErrorMessages.INVALID_EMAIL_FORMAT);
        }
        User creatorUser = iUserServicePort.getUser(emailCreator);
        if (creatorUser == null){
            throw new CustomException(ConstantsErrorMessages.USER_NOT_FOUND);
        }
        User newUser = userRequestMapper.toUser(userDTORq);
        iUserServicePort.saveUserOwner(newUser,creatorUser);
    }

    @Override
    public List<UserDTOResponse> getAllUserDTO() {
        return List.of();
    }

    @Override
    public UserDTOResponse getUserDTO(String email) {
        return null;
    }
}
