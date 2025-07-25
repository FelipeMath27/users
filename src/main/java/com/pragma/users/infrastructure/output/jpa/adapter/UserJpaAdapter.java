package com.pragma.users.infrastructure.output.jpa.adapter;

import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.utils.ConstantsErrorMessages;
import com.pragma.users.infrastructure.exceptions.InfrastructureException;
import com.pragma.users.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.users.infrastructure.output.jpa.repository.IUserRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUserOwner(User user) {
        try {
            UserEntity userEntity = userEntityMapper.toUserEntity(user);
            userRepository.save(userEntity);
        } catch (DataAccessException | PersistenceException ex){
            throw new InfrastructureException(ConstantsErrorMessages.USER_NOT_SAVED, ex);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        return userEntityOptional.map(userEntityMapper::toUser).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,ConstantsErrorMessages.USER_NOT_FOUND));
    }

    @Override
    public User getUserById(Long idUser) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(idUser);
        return userEntityOptional.map(userEntityMapper::toUser).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,ConstantsErrorMessages.USER_NOT_FOUND));
    }

    @Override
    public void saveAdmin(User user) {
        try {
            userRepository.save(userEntityMapper.toUserEntity(user));
        } catch (DataAccessException | PersistenceException ex){
            throw new InfrastructureException(ConstantsErrorMessages.USER_NOT_SAVED, ex);
        }
    }
}
