package com.pragma.users.application.handler;

import com.pragma.users.application.dto.RolDTOResponse;
import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.application.mapper.RolResponseMapper;
import com.pragma.users.application.mapper.UserRequestMapper;
import com.pragma.users.application.mapper.UserResponseMapper;
import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;

import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.usecase.UseCaseUser;
import com.pragma.users.domain.validator.ValidatorCases;
import com.pragma.users.infrastructure.exceptions.ConstantsErrorMessages;
import com.pragma.users.infrastructure.exceptions.CustomException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class UserHandler implements IUserHandler{

    private static final Logger logger = LoggerFactory.getLogger(UseCaseUser.class);

    private final IUserServicePort iUserServicePort;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final IRolServicePort iRolServicePort;
    private final RolResponseMapper rolResponseMapper;

    public UserHandler(IUserServicePort iUserServicePort, UserRequestMapper userRequestMapper,
                       UserResponseMapper userResponseMapper, IRolServicePort iRolServicePort, RolResponseMapper rolResponseMapper) {
        this.iUserServicePort = iUserServicePort;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
        this.iRolServicePort = iRolServicePort;
        this.rolResponseMapper = rolResponseMapper;
    }

    @Override
    public void saveUserDTOOwner(UserDTORequest userDTORq, String emailCreator) {
        if(!ValidatorCases.isValidEmail(emailCreator) ||
                !ValidatorCases.isValidEmail(userDTORq.getEmail())){
            throw new CustomException(ConstantsErrorMessages.INVALID_EMAIL_FORMAT);
        }
        logger.info("Son validos el correo admi: {} y el correo nuevo {}", emailCreator, userDTORq.getEmail());
        User creatorUser = iUserServicePort.getUser(emailCreator);
        if (creatorUser == null){
            throw new CustomException(ConstantsErrorMessages.USER_NOT_FOUND);
        }
        User newUser = userRequestMapper.toUser(userDTORq);
        logger.info("se mapea el dto {} a usuario ignoranro el rol {}", userDTORq, newUser);
        Rol userRol = iRolServicePort.getRolByName(userDTORq.getNameRol());
        if (userRol == null) {
            throw new CustomException(ConstantsErrorMessages.ROL_NOT_FOUND);
        }
        logger.info("Rol antes de ingresa a newUser {}", userRol);
        newUser.setRol(userRol);
        logger.info("Roll luetgo de ingresar a usr {}",newUser.getRol());
        iUserServicePort.saveUserOwner(newUser,emailCreator);
    }

        @Override
        public UserDTOResponse getUserDTO(String email) {
            return userResponseMapper.toUserDtoResponse(iUserServicePort.getUser(email));
        }
}
