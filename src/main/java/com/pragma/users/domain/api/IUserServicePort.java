package com.pragma.users.domain.api;

import com.pragma.users.domain.model.User;

public interface IUserServicePort {
    void saveUserOwner(User user);

    User getUser(String email);

    User getUserById(Long idUser);

    void saveAdmin(User user);
}
