package com.pragma.users.domain.usecase;


import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeDocumentEnum;
import com.pragma.users.domain.model.TypeRolEnum;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.validator.ValidatorCases;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import com.pragma.users.infrastructure.exceptions.CustomException;
import com.pragma.users.infrastructure.security.PasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UseCaseUser implements IUserServicePort {
    private static final Logger logger = LoggerFactory.getLogger(UseCaseUser.class);

    private final IUserPersistencePort userPersistencePort;
    private final IRolServicePort rolServicePort;
    private final PasswordService passwordService;

    @Override
    public void saveUserOwner(User newUser,String emailCreatorUser) {
        log.info(ConstantsErrorMessages.START_FLOW);
        validateAdminCreator(emailCreatorUser);
        validateOwnerRole(newUser);
        processValidateSaveUser(newUser);
    }

    private void validateAdminCreator(String emailCreatorUser) {
        log.info(ConstantsErrorMessages.START_VALIDATE_CREATOR_USER);
        ValidatorCases.validateEmail(emailCreatorUser)
                .flatMap(email -> Optional.ofNullable(userPersistencePort.getUserByEmail(email)))
                .filter(creator -> TypeRolEnum.ADMIN.name().equals(creator.getRol().getNameRol()))
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.PERMISSION_DENIED));
    }

    private void validateOwnerRole(User user) {
        log.info(ConstantsErrorMessages.START_VALIDATE_OWNER);
        Optional.ofNullable(user.getRol())
                .map(rol -> {
                    Rol fetchedRol = rolServicePort.getRolByName(rol.getNameRol());
                    logger.info(fetchedRol.getNameRol());
                    return fetchedRol;
                })
                .filter(rol -> {
                    boolean isOwner = TypeRolEnum.OWNER.name().equals(rol.getNameRol());
                    logger.info(String.valueOf(isOwner));
                    return isOwner;
                })
                .ifPresentOrElse(
                        user::setRol,
                        () -> {
                            logger.error(ConstantsErrorMessages.INVALID_ROLE,"{}", user.getRol());
                            throw new CustomException(ConstantsErrorMessages.INVALID_ROLE);
                        }
                );
    }

    private void processValidateSaveUser(User user){
        log.info(ConstantsErrorMessages.START_PROCESS_TO_VALIDATE_CONDITION);
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
        user.setNameUser(ValidatorCases.sanitize(user.getNameUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.NAME_CANT_BE_NULL)));
        user.setLastNameUser(ValidatorCases.sanitize(user.getLastNameUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.LAST_NAME_CANT_BE_NULL)));
        userPersistencePort.saveUserOwner(user);
    }

    @Override
    public User getUser(String email) {
        return ValidatorCases.sanitize(email)
                .map(userPersistencePort::getUserByEmail)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.CANT_BE_NULL));
    }

    @Override
    public User getUserById(Long idUser) {
        return Optional.ofNullable(idUser)
                .filter(id -> id > 0)
                .map(userPersistencePort::getUserById)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.CANT_BE_NULL));
    }
}
