package com.pragma.users.domain.usecase;


import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.model.TypeDocumentEnum;
import com.pragma.users.domain.model.TypeRolEnum;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.validator.ValidatorCases;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import com.pragma.users.infrastructure.exceptions.CustomException;
import com.pragma.users.infrastructure.security.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;


public class UseCaseUser implements IUserServicePort {
    private static final Logger logger = LoggerFactory.getLogger(UseCaseUser.class);

    private final IUserPersistencePort userPersistencePort;
    private final IRolServicePort rolServicePort;
    private final PasswordService passwordService;

    public UseCaseUser(IUserPersistencePort userPersistencePort,
                       IRolServicePort rolServicePort,
                       PasswordService passwordService){
        this.userPersistencePort = userPersistencePort;
        this.rolServicePort = rolServicePort;
        this.passwordService = passwordService;
    }

    @Override
    public void saveUserOwner(User newUser,String emailCreatorUser) {
        validateAdminCreator(emailCreatorUser);
        validateOwnerRole(newUser);
        validateUserNames(newUser);
        processValidateSaveUser(newUser);
    }

    private void validateAdminCreator(String emailCreatorUser) {
        ValidatorCases.validateEmail(emailCreatorUser)
                .flatMap(email -> Optional.ofNullable(userPersistencePort.getUserByEmail(email)))
                .filter(creator -> TypeRolEnum.ADMIN.name().equals(creator.getRol().getNameRol()))
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.PERMISSION_DENIED));
    }

    private void validateOwnerRole(User user) {
        Optional.ofNullable(user.getRol())
                .map(rol -> rolServicePort.getRolByName(rol.getNameRol()))
                .filter(rol -> TypeRolEnum.OWNER.name().equals(rol.getNameRol()))
                .ifPresentOrElse(
                        user::setRol,
                        () -> {
                            throw new CustomException(ConstantsErrorMessages.INVALID_ROLE);
                        }
                );
    }

    private void validateUserNames(User user) {
        user.setNameUser(ValidatorCases.sanitize(user.getNameUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.NAME_CANT_BE_NULL)));
        user.setLastNameUser(ValidatorCases.sanitize(user.getLastNameUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.LAST_NAME_CANT_BE_NULL)));
    }

    private void processValidateSaveUser(User user){
        user.setEmail(ValidatorCases.validateEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.INVALID_EMAIL_FORMAT)));
        user.setDocumentUser(ValidatorCases.validateDocumentNumber(TypeDocumentEnum.CC,user.getDocumentUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.INVALID_DOCUMENT_FORMAT)));
        user.setPhoneNumberUser(ValidatorCases.validatePhoneNumber(user.getPhoneNumberUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.INVALID_PHONE_FORMAT)));
        ValidatorCases.validateIsAdult(user.getDateBirthUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.USER_UNDERAGE));
        user.setPassword(passwordService.encryptPassword(ValidatorCases.sanitize(user.getPassword())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.PASSWORD_CANNOT_BE_EMPTY))));
        userPersistencePort.saveUserOwner(user);
    }

    @Override
    public User getUser(String email) {
        return userPersistencePort.getUserByEmail(email);
    }

    @Override
    public User getUserById(Long idUser) {
        return userPersistencePort.getUserById(idUser);
    }
}
