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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserPersistencePort iUserPersistencePort;

    @Mock
    private IRolServicePort rolServicePort;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private UseCaseUser useCaseUser;

    private User newUser, adminUser;

    @BeforeEach
    void setUp(){
        adminUser = new User(UUID.randomUUID(),
                "Admin", "AdminTest",
                TypeDocumentEnum.CC,"1015446175",
                "+573142212051",
                LocalDate.of(1994,5,27),
                "Admin@gmail.com","admin123",
                new Rol(1L,"ADMIN","Admin role, this role cab be" +
                        "admin the platform"));

        newUser = new User(UUID.randomUUID(),
                "New User", "NewUserTest",
                TypeDocumentEnum.CC,"1019075310",
                "+573125018091",
                LocalDate.of(1992,9,8),
                "owner@gmail.com","owner123", null);
    }

    @Test
    void saveUserOwner_AdminCreate(){
        when(iUserPersistencePort.getUserByEmail(adminUser.getEmail())).thenReturn(adminUser);
        Rol ownerRolMock = new Rol(2L, TypeRolEnum.OWNER.name(),"Owner rol, to be restaurants owner");
        when(rolServicePort.getRol(TypeRolEnum.OWNER.name())).thenReturn(ownerRolMock);
        when(passwordService.encryptPassword(anyString())).thenReturn("encryptedPassword");
        useCaseUser.saveUserOwner(newUser,adminUser.getEmail());

        assertEquals(ownerRolMock,newUser.getRol());
        assertEquals("encryptedPassword",newUser.getPassword());
        verify(iUserPersistencePort).saveUserOwner(newUser);
    }

    @Test
    void saveUserOwner_emailCreatorNotFound(){
        when(iUserPersistencePort.getUserByEmail("no@gmail.com")).thenReturn(null);
        CustomException exception = assertThrows(CustomException.class, ()-> useCaseUser.saveUserOwner(newUser,"no@gmail.com"));
        assertEquals(ConstantsErrorMessages.CANT_BE_NULL,exception.getMessage());
    }



}
