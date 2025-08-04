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
import com.pragma.users.infrastructure.security.IPasswordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UseCaseUser implements IUserServicePort {

    private final IUserPersistencePort iuserPersistencePort;
    private final IRolServicePort irolServicePort;
    private final IPasswordService ipasswordService;

    @Override
    public void saveUserOwner(User newUser) {
        log.info(ConstantsErrorMessages.START_FLOW);
        validateOwnerRole(newUser);
        processValidateSaveUser(newUser);
        log.info(ConstantsErrorMessages.END_SUCCESSFUL_FLOW);
    }

    private void validateOwnerRole(User user) {
        log.info(ConstantsErrorMessages.START_VALIDATE_OWNER);
        Optional.ofNullable(user.getRol())
                .map(rol -> {
                    Rol fetchRol = Optional.ofNullable(irolServicePort.getRolByName(rol.getNameRol()))
                            .orElseThrow(()-> {
                                log.error(ConstantsErrorMessages.ROL_NOT_FOUND);
                                return new CustomException(ConstantsErrorMessages.ROL_NOT_FOUND);
                            });
                    user.setRol(fetchRol);
                    log.info(fetchRol.getNameRol());
                    return fetchRol;
                }).filter(rol -> {
                    boolean isOwner = TypeRolEnum.OWNER.name().equals(rol.getNameRol());
                    if (!isOwner) {
                        log.error(ConstantsErrorMessages.ROL_NOT_FOUND);
                    }
                    return isOwner;
                }).orElseThrow(() -> {
                    log.error(ConstantsErrorMessages.ROL_REQUIRED);
                    return new CustomException(ConstantsErrorMessages.ROL_REQUIRED);
                });
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
        user.setPassword(ipasswordService.encryptPassword(ValidatorCases.sanitize(user.getPassword())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.PASSWORD_CANNOT_BE_EMPTY))));
        user.setNameUser(ValidatorCases.sanitize(user.getNameUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.NAME_CANT_BE_NULL)));
        user.setLastNameUser(ValidatorCases.sanitize(user.getLastNameUser())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.LAST_NAME_CANT_BE_NULL)));
        iuserPersistencePort.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        String emailSanitized = ValidatorCases.sanitize(email)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.CANT_BE_NULL));
        return iuserPersistencePort.findByEmail(emailSanitized)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.USER_NOT_FOUND));
    }

    @Override
    public User getUserById(Long idUser) {
        Long validateId = Optional.ofNullable(idUser)
                .filter(id -> id > 0)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.INVALID_ID));
        return iuserPersistencePort.findById(validateId)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.USER_NOT_FOUND));
    }

    @Override
    public void saveEmployeeUser(User user) {
        log.info(ConstantsErrorMessages.START_FLOW);
        validateEmployee(user);
        user.setPassword(ipasswordService.encryptPassword(ValidatorCases.sanitize(user.getPassword())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessages.PASSWORD_CANNOT_BE_EMPTY))));
        iuserPersistencePort.save(user);
        log.info(ConstantsErrorMessages.END_SUCCESSFUL_FLOW);
    }

    private void validateEmployee(User user){
        log.info(ConstantsErrorMessages.START_VALIDATE_EMPLOYEE);
        Optional.ofNullable(user.getRol())
                .map(rol -> {
                    Rol fetchRol = Optional.ofNullable(irolServicePort.getRolByName(rol.getNameRol()))
                            .orElseThrow(()-> {
                                log.error(ConstantsErrorMessages.ROL_NOT_FOUND);
                                return new CustomException(ConstantsErrorMessages.ROL_NOT_FOUND);
                            });
                    user.setRol(fetchRol);
                    log.info(fetchRol.getNameRol());
                    return fetchRol;
                }).filter(rol -> {
                    boolean isOwner = TypeRolEnum.EMPLOYEE.name().equals(rol.getNameRol());
                    if (!isOwner) {
                        log.error(ConstantsErrorMessages.ROL_NOT_FOUND);
                    }
                    return isOwner;
                }).orElseThrow(() -> {
                    log.error(ConstantsErrorMessages.ROL_REQUIRED);
                    return new CustomException(ConstantsErrorMessages.ROL_REQUIRED);
                });
    }

    @Override
    public void saveAdmin(User user) {
        user.setPassword(ipasswordService.encryptPassword(user.getPassword()));
        iuserPersistencePort.save(user);
    }
}
