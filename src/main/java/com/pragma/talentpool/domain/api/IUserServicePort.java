package com.pragma.talentpool.domain.api;

import com.pragma.talentpool.domain.model.User;
import java.util.List;

public interface IUserServicePort {
    void saveUser(User user);

    List<User> getAllUser();

    User getUser(String email);

    void updateUser(User user);

    void deleteUser(String email);
}
