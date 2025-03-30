package com.pragma.users.domain.api;

import com.pragma.users.domain.model.User;
import java.util.List;

public interface IUserServicePort {
    void saveUserOwner(User user, String emailCreatorUser);

    User getUser(String email);

}
