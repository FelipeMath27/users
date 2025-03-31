package com.pragma.users.unit;

import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeDocumentEnum;
import com.pragma.users.domain.model.TypeRolEnum;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.usecase.UseCaseUser;
import com.pragma.users.infrastructure.exceptions.ConstantsErrorMessages;
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


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Create_owner(){
        Rol adminRol = new Rol(1L,TypeRolEnum.ADMIN.name(),"Admin role");
        User creatorUser = new User(1L,"Tania","Supelano",
                TypeDocumentEnum.CC,"1019","+57312501",
                LocalDate.of(1992,9,8),"tania@gmail.com",
                "admin123",adminRol);

        when(iUserPersistencePort.getUserByEmail(anyString())).thenReturn(creatorUser);

        Rol ownerRol = new Rol(2L,TypeRolEnum.OWNER.name(),"Owner Rol");
        when(rolServicePort.getRolByName(anyString())).thenReturn(ownerRol);
        when(passwordService.encryptPassword(anyString())).thenReturn("encryptedPassword");

        User newUserOwner = new User(2L,"Felipe","Supelano",
                TypeDocumentEnum.CC,"1015","+57314221",
                LocalDate.of(1994,5,27),"felipe@gmail.com",
                "owner123",ownerRol);

        useCaseUser.saveUserOwner(newUserOwner,creatorUser.getEmail());

        verify(iUserPersistencePort, times(1)).saveUserOwner(any(User.class));

    }

    @Test
    void test_admin_email_invalid(){
        User newUserOwner = new User(2L, "Felipe", "Supelano",
                TypeDocumentEnum.CC, "1015", "+57314221",
                LocalDate.of(1994, 5, 27), "felipe@gmail.com",
                "owner123", null);

        assertThrows(CustomException.class, () -> {
            useCaseUser.saveUserOwner(newUserOwner, "invalid-email");
        });
    }

    @Test
    void test_admin_not_found_email(){
        Rol creatorRol = new Rol(2L, TypeRolEnum.OWNER.name(), "Owner Role");
        User creatorOwner = new User(2L, "Felipe", "Supelano",
                TypeDocumentEnum.CC, "101655", "+573142",
                LocalDate.of(1994, 5, 27), "fel@gmail.com",
                "owner123", creatorRol);

        Rol userRol = new Rol(2L, TypeRolEnum.OWNER.name(), "Owner Role");
        User newUserOwner = new User(1L, "Felipe", "Supelano",
                TypeDocumentEnum.CC, "1015", "+57314221",
                LocalDate.of(1994, 5, 27), "felipe@gmail.com",
                "owner123", userRol);

        when(iUserPersistencePort.getUserByEmail(anyString())).thenReturn(creatorOwner);

        CustomException exception = assertThrows(CustomException.class, () -> {
            useCaseUser.saveUserOwner(newUserOwner, creatorOwner.getEmail());
        });

        assertEquals(ConstantsErrorMessages.PERMISSION_DENIED, exception.getMessage());

        verify(iUserPersistencePort, never()).saveUserOwner(any(User.class));
    }

    @Test
    void test_User_isAdult() {
        Rol ownerRol = new Rol(2L, TypeRolEnum.OWNER.name(), "Owner role");
        User newUser = new User(2L, "Felipe", "Supelano",
                TypeDocumentEnum.CC, "1015446175", "+573142212051",
                LocalDate.now().minus(Period.ofYears(17)), "felipe@gmail.com",
                "owner123", ownerRol);

        when(rolServicePort.getRolByName(anyString())).thenReturn(ownerRol);

        assertThrows(CustomException.class, () -> {
            useCaseUser.saveUserOwner(newUser, "admin@gmail.com");
        });
    }

    @Test
    void test_Email_invalid() {
        Rol ownerRol = new Rol(2L, TypeRolEnum.OWNER.name(), "Owner role");
        User newUser = new User(2L, "Felipe", "Supelano",
                TypeDocumentEnum.CC, "1015446175", "+573142212051",
                LocalDate.of(1994, 5, 27), "felipe_at_gmail.com",
                "owner123", ownerRol);

        when(rolServicePort.getRolByName(anyString())).thenReturn(ownerRol);

        assertThrows(CustomException.class, () -> {
            useCaseUser.saveUserOwner(newUser, "admin@gmail.com");
        });
    }

    @Test
    void test_Phone_Number_invalid() {
        Rol ownerRol = new Rol(2L, TypeRolEnum.OWNER.name(), "Owner role");
        User newUser = new User(2L, "Felipe", "Supelano",
                TypeDocumentEnum.CC, "1015446175", "+573142212051234",
                LocalDate.of(1994, 5, 27), "felipe@gmail.com",
                "owner123", ownerRol);

        when(rolServicePort.getRolByName(anyString())).thenReturn(ownerRol);

        assertThrows(CustomException.class, () -> {
            useCaseUser.saveUserOwner(newUser, "admin@gmail.com");
        });
    }

    @Test
    void test_Document_Identity_Numeric() {
        Rol ownerRol = new Rol(2L, TypeRolEnum.OWNER.name(), "Owner role");
        User newUser = new User(2L, "Felipe", "Supelano",
                TypeDocumentEnum.CC, "1015a446175", "+573142212051",
                LocalDate.of(1994, 5, 27), "felipe@gmail.com",
                "owner123", ownerRol);

        when(rolServicePort.getRolByName(anyString())).thenReturn(ownerRol);

        assertThrows(CustomException.class, () -> {
            useCaseUser.saveUserOwner(newUser, "admin@gmail.com");
        });
    }
}



