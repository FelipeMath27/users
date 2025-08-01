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
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private IUserPersistencePort iUserPersistencePort;

    @Mock
    private IRolServicePort rolServicePort;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private UseCaseUser useCaseUser;

    private Rol ownerRol,adminRol;
    private User creatorUser, newUserOwner;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        adminRol = new Rol(1L, TypeRolEnum.ADMIN.name(), "Admin role");
        creatorUser = new User(1L,"Tania","Supelano",
                TypeDocumentEnum.CC,"1019","+57312501",
                LocalDate.of(1992,9,8),"tania@gmail.com",
                "admin123", adminRol);
        ownerRol = new Rol(2L,TypeRolEnum.OWNER.name(),"Owner Rol");
        newUserOwner = new User(2L,"Felipe","Supelano",
                TypeDocumentEnum.CC,"1015","+57314221",
                LocalDate.of(1994,5,27),"felipe@gmail.com",
                "owner123",ownerRol);
        when(iUserPersistencePort.getUserByEmail(anyString())).thenReturn(creatorUser);
        when(rolServicePort.getRolByName(anyString())).thenReturn(ownerRol);
        when(passwordService.encryptPassword(anyString())).thenReturn("encryptedPassword");
    }

    @Test
    void test_Create_owner(){
        useCaseUser.saveUserOwner(newUserOwner);
        verify(iUserPersistencePort, times(1)).saveUserOwner(any(User.class));
    }

    @Test
    void test_email_admin_null(){
        creatorUser.setEmail(null);
        CustomException exception = assertThrows(CustomException.class,()->{
            useCaseUser.saveUserOwner(newUserOwner);
        });
        assertEquals(ConstantsErrorMessages.ADMIN_NOT_FOUND,exception.getMessage());
        verify(iUserPersistencePort,never()).saveUserOwner(any(User.class));
    }

    @Test
    void test_creator_owner_isNot_Admin(){
        Rol emp = new Rol(3L,TypeRolEnum.CLIENT.name(), "Client rol");
        creatorUser.setRol(emp);
        CustomException exception = assertThrows(CustomException.class,()->{
            useCaseUser.saveUserOwner(newUserOwner);
        });
        assertEquals(ConstantsErrorMessages.PERMISSION_DENIED,exception.getMessage());
        verify(iUserPersistencePort,never()).saveUserOwner(any(User.class));
    }

}



