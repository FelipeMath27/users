package com.pragma.users.domain.spi;

import com.pragma.users.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUserOwner(User user);

    User getUserByEmail(String email);
}
