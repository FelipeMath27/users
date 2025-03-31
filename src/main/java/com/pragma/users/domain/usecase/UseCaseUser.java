package com.pragma.users.domain.usecase;


import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeRolEnum;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.validator.ValidatorCases;
import com.pragma.users.infrastructure.exceptions.ConstantsErrorMessages;
import com.pragma.users.infrastructure.exceptions.CustomException;
import com.pragma.users.infrastructure.security.PasswordService;

public class UseCaseUser implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolServicePort rolServicePort;
    private final PasswordService passwordService;

    public UseCaseUser(IUserPersistencePort userPersistencePort, IRolServicePort rolServicePort, PasswordService passwordService){
        this.userPersistencePort = userPersistencePort;
        this.rolServicePort = rolServicePort;
        this.passwordService = passwordService;
    }

    @Override
    public void saveUserOwner(User newUser, String emailCreatorUser) {
        if (emailCreatorUser == null){
            throw new CustomException(ConstantsErrorMessages.CANT_BE_NULL);
        }
        User creatorUser = userPersistencePort.getUserByEmail(emailCreatorUser);
        if(creatorUser == null){
            throw new CustomException(ConstantsErrorMessages.ADMIN_NOT_FOUND);
        }
        if(!TypeRolEnum.ADMIN.name().equals(creatorUser.getRol().getNameRol())){
            throw new CustomException(ConstantsErrorMessages.PERMISSION_DENIED);
        }
        Rol ownerRol = rolServicePort.getRolByName(TypeRolEnum.OWNER.name());
        newUser.setRol(ownerRol);
        newUser.setEmail(ValidatorCases.sanitizeString(newUser.getEmail()));
        newUser.setPhoneNumberUser(ValidatorCases.sanitizeString(newUser.getPhoneNumberUser()));
        newUser.setDocumentUser(ValidatorCases.sanitizeString(newUser.getDocumentUser()));
        validateUser(newUser);
        newUser.setPassword(passwordService.encryptPassword(newUser.getPassword()));
        userPersistencePort.saveUserOwner(newUser);
    }

    private void validateUser(User user) {
        if(user.getNameUser() == null || user.getNameUser().isBlank()){
            throw new CustomException(ConstantsErrorMessages.CANT_BE_NULL);
        }
        if(user.getLastNameUser() == null || user.getLastNameUser().isBlank()){
            throw new CustomException(ConstantsErrorMessages.CANT_BE_NULL);
        }
        if(user.getTypeDocumentUser() == null){
            throw new CustomException(ConstantsErrorMessages.CANT_BE_NULL);
        }
        if(user.getDateBirthUser() == null){
            throw new CustomException(ConstantsErrorMessages.CANT_BE_NULL);
        }
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
    public User getUser(String email) {
        return userPersistencePort.getUserByEmail(email);
    }
}
