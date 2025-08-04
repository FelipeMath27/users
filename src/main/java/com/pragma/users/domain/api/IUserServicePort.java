package com.pragma.users.domain.api;

import com.pragma.users.domain.model.User;

public interface IUserServicePort {
    void saveUserOwner(User user);

    User getUserByEmail(String email);

    User getUserById(Long idUser);

    void saveEmployeeUser(User user);

    void saveAdmin(User user);
}
