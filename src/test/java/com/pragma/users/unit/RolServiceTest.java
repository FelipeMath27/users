package com.pragma.users.unit;

import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeRolEnum;
import com.pragma.users.domain.spi.IRolPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    private IRolPersistencePort iRolPersistencePort;

    @Test
    void test_get_rol_by_name(){
        Rol adminRol = new Rol(1L, TypeRolEnum.ADMIN.name(),"Admin role");
        when(iRolPersistencePort.getRolByName(adminRol.getNameRol())).thenReturn(adminRol);

        iRolPersistencePort.getRolByName(adminRol.getNameRol());


        verify(iRolPersistencePort, times(1)).getRolByName(adminRol.getNameRol());
    }
}
