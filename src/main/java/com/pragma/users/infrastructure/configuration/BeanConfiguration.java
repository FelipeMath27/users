package com.pragma.users.infrastructure.configuration;

import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.spi.IRolPersistencePort;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.usecase.UseCaseRol;
import com.pragma.users.domain.usecase.UseCaseUser;
import com.pragma.users.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.pragma.users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.users.infrastructure.output.jpa.repository.IRolRepository;
import com.pragma.users.infrastructure.output.jpa.repository.IUserRepository;
import com.pragma.users.infrastructure.security.PasswordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    private final IUserRepository iuserRepository;
    private final UserEntityMapper userEntityMapper;
    private final RolEntityMapper rolEntityMapper;
    private final IRolRepository irolRepository;

    public BeanConfiguration(IUserRepository iuserRepository, UserEntityMapper userEntityMapper, RolEntityMapper rolEntityMapper, IRolRepository irolRepository) {
        this.iuserRepository = iuserRepository;
        this.userEntityMapper = userEntityMapper;
        this.rolEntityMapper = rolEntityMapper;
        this.irolRepository = irolRepository;
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort iUserPersistencePort,
                                            IRolServicePort iRolServicePort, PasswordService passwordService){
        return new UseCaseUser(iUserPersistencePort,iRolServicePort,passwordService);
    }

    @Bean
    public IRolServicePort rolServicePort (IRolPersistencePort rolPersistencePort){
        return new UseCaseRol( rolPersistencePort);
    }
}
