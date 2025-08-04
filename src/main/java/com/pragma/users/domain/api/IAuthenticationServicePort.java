package com.pragma.users.domain.api;


public interface IAuthenticationServicePort {
    String login(String email, String password);
}
