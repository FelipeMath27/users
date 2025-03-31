package com.pragma.users.application.handler;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.application.mapper.UserRequestMapper;
import com.pragma.users.domain.api.IUserServicePort;

import com.pragma.users.domain.model.User;
import com.pragma.users.domain.validator.ValidatorCases;
import com.pragma.users.infrastructure.exceptions.ConstantsErrorMessages;
import com.pragma.users.infrastructure.exceptions.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserHandler implements IUserHandler{
    private final IUserServicePort iUserServicePort;
    private final UserRequestMapper userRequestMapper;

    public UserHandler(IUserServicePort iUserServicePort, UserRequestMapper userRequestMapper) {
        this.iUserServicePort = iUserServicePort;
        this.userRequestMapper = userRequestMapper;
    }

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
        iUserServicePort.saveUserOwner(newUser,emailCreator);
    }

    @Override
    public UserDTOResponse getUserDTO(String email) {
        return null;
    }
}
