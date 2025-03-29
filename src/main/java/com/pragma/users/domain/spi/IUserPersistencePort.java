package com.pragma.users.domain.spi;

import com.pragma.users.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUserOwner(User user);

    List<User> getAllUser();

    User getUser(String email);

}
