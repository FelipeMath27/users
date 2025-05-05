package com.pragma.users.infrastructure.configuration;

import com.pragma.users.domain.api.IAuthenticationServicePort;
import com.pragma.users.domain.api.IRolServicePort;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.spi.IRolPersistencePort;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.usecase.AuthenticationUseCase;
import com.pragma.users.domain.usecase.UseCaseRol;
import com.pragma.users.domain.usecase.UseCaseUser;
import com.pragma.users.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.pragma.users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.users.infrastructure.output.jpa.repository.IRolRepository;
import com.pragma.users.infrastructure.output.jpa.repository.IUserRepository;
import com.pragma.users.infrastructure.security.IJwtTokenProvider;
import com.pragma.users.infrastructure.security.IPasswordService;
import com.pragma.users.infrastructure.security.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BeanConfiguration {
    private final IUserRepository iuserRepository;
    private final UserEntityMapper userEntityMapper;
    private final RolEntityMapper rolEntityMapper;
    private final IRolRepository irolRepository;

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort iUserPersistencePort,
                                            IRolServicePort iRolServicePort, PasswordService passwordService){
        return new UseCaseUser(iUserPersistencePort,iRolServicePort,passwordService);
    }

    @Bean
    public IRolServicePort rolServicePort (IRolPersistencePort rolPersistencePort){
        return new UseCaseRol(rolPersistencePort);
    }

    @Bean
    public IAuthenticationServicePort iAuthenticationServicePort (IPasswordService iPasswordService,
                                                                  IJwtTokenProvider iJwtTokenProvider,
                                                                  IUserPersistencePort iUserPersistencePort){
        return new AuthenticationUseCase(iPasswordService,iJwtTokenProvider,iUserPersistencePort);
    }
}
