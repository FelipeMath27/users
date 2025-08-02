package com.pragma.users.unit;

import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeDocumentEnum;
import com.pragma.users.domain.model.TypeRolEnum;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.usecase.UseCaseUser;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import com.pragma.users.infrastructure.exceptions.CustomException;
import com.pragma.users.infrastructure.security.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class UserServiceTest {

    @Mock
    private IUserPersistencePort iUserPersistencePort;

    @Mock
    private IRolServicePort rolServicePort;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private UseCaseUser useCaseUser;

    private User newUserOwner, creatorUser;
    private Rol adminRol, ownerRol;

    private CustomException customException;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        adminRol = new Rol(1L, TypeRolEnum.ADMIN.name(), "Admin role");
        creatorUser = new User(1L, "Tania", "Supelano",
                TypeDocumentEnum.CC, "1019", "+57312501",
                LocalDate.of(1992, 9, 8), "tania@gmail.com",
                "admin123", adminRol);
        ownerRol = new Rol(2L, TypeRolEnum.OWNER.name(), "Owner Rol");
        newUserOwner = new User(2L,"Felipe","Supelano",
                TypeDocumentEnum.CC,"1015","+57314221",
                LocalDate.of(1994,5,27),"felipe@gmail.com",
                "owner123", ownerRol);
        when(iUserPersistencePort.findByEmail(anyString())).thenReturn(Optional.of(creatorUser));
        when(rolServicePort.getRolByName(anyString())).thenReturn(ownerRol);
        when(passwordService.encryptPassword(anyString())).thenReturn("encryptedPassword");
    }

    @Test
    void test_Create_owner(){
        useCaseUser.saveUserOwner(newUserOwner);
        verify(iUserPersistencePort, times(1)).save(any(User.class));
    }

    @Test
    void test_exception_whenSomeFieldIsNull(){
        creatorUser.setNameUser(null);
        verify(iUserPersistencePort, never()).save(any(User.class));
        customException = assertThrows(CustomException.class, () -> useCaseUser.saveUserOwner(creatorUser));
        assertEquals(ConstantsErrorMessages.NAME_CANT_BE_NULL, customException.getMessage());
    }

    @Test
    void test_exception_whenIsNotAdult(){
        creatorUser.setDateBirthUser(LocalDate.of(2009,1,1));
        customException = assertThrows(CustomException.class, () -> useCaseUser.saveUserOwner(creatorUser));
        assertEquals(ConstantsErrorMessages.USER_UNDERAGE, customException.getMessage());
    }

   @Test
   void test_exception_whenEmailHasWrongFormat(){
        creatorUser.setEmail("Felipecomfelipe.");
        customException = assertThrows(CustomException.class, () -> useCaseUser.saveUserOwner(creatorUser));
        assertEquals(ConstantsErrorMessages.INVALID_EMAIL_FORMAT, customException.getMessage());
   }

   @Test
    void test_exception_whenFormatPhoneNumberIsInvalid(){
        creatorUser.setPhoneNumberUser("+012231232");
        customException = assertThrows(CustomException.class, () -> useCaseUser.saveUserOwner(creatorUser));
        assertEquals(ConstantsErrorMessages.INVALID_PHONE_FORMAT,customException.getMessage());
   }

   @Test
    void test_exception_whenDocumentIsInvalid(){
        creatorUser.setDocumentUser("AB21312");
        customException = assertThrows(CustomException.class, () -> useCaseUser.saveUserOwner(creatorUser));
        assertEquals(ConstantsErrorMessages.INVALID_DOCUMENT_FORMAT,customException.getMessage());
   }

}



