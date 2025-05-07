package com.pragma.users.domain.api;

import com.pragma.users.domain.model.User;

public interface IAuthenticationServicePort {
    String login(String email, String password);
}
