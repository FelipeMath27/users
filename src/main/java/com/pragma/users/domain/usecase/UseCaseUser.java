package com.pragma.users.domain.usecase;


import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeRolEnum;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.validator.ValidatorCases;
import com.pragma.users.infraestructure.exceptions.ConstantsErrorMessages;
import com.pragma.users.infraestructure.exceptions.CustomException;

import java.util.List;

public class UseCaseUser implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolServicePort rolServicePort;

    public UseCaseUser(IUserPersistencePort userPersistencePort, IRolServicePort rolServicePort){
        this.userPersistencePort = userPersistencePort;
        this.rolServicePort = rolServicePort;
    }

    @Override
    public void saveUser(User newUser, User creatorUser) {
        if (creatorUser.getRol() == null){
            throw new CustomException(ConstantsErrorMessages.ROLE_REQUIRED);
        }

        if(!ValidatorCases.hasRole(creatorUser,TypeRolEnum.ADMIN.name())){
            throw new CustomException(ConstantsErrorMessages.PERMISSION_DENIED);
        }
        Rol ownerRol = rolServicePort.getRol(TypeRolEnum.OWNER.name());
        newUser.setRol(ownerRol);
        newUser.setEmail(ValidatorCases.sanitizeString(newUser.getEmail()));
        newUser.setPhoneNumberUser(ValidatorCases.sanitizeString(newUser.getPhoneNumberUser()));
        validateUser(newUser);
        userPersistencePort.saveUser(newUser);
    }


    private void validateUser(User user) {
        if(!ValidatorCases.isValidEmail(user.getEmail())){
            throw new CustomException(ConstantsErrorMessages.INVALID_EMAIL_FORMAT);
        }
        if (!ValidatorCases.isValidPhone(user.getPhoneNumberUser())){
            throw new CustomException(ConstantsErrorMessages.INVALID_PHONE_FORMAT);
        }
        if(!ValidatorCases.isValidDocument(user.getTypeDocumentUser(),user.getDocumentUser())){
            throw new CustomException(ConstantsErrorMessages.INVALID_DOCUMENT_FORMAT);
        }
        if(!ValidatorCases.isAdult(user.getDateBirthUser())){
            throw new CustomException(ConstantsErrorMessages.USER_UNDERAGE);
        }
    }


    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUser(String email) {
        return null;
    }




}
