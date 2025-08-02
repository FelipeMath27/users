package com.pragma.users.domain.spi;

import com.pragma.users.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long idUser);
}
