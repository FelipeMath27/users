package com.pragma.users.infrastructure.output.jpa.adapter;

import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.infrastructure.exceptions.ConstantsErrorMessages;
import com.pragma.users.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.users.infrastructure.output.jpa.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserJpaAdapter(IUserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public void saveUserOwner(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        return userEntityOptional.map(userEntityMapper::toUser).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,ConstantsErrorMessages.USER_NOT_FOUND));
    }

}
