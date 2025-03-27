package com.pragma.users.domain.api;

import com.pragma.users.domain.model.User;
import java.util.List;

public interface IUserServicePort {
    void saveUser(User user, User creatorUser);

    List<User> getAllUsers();

    User getUser(String email);

}
