package com.pragma.users.application.handler;


import com.pragma.users.application.dto.LoginDTORequest;
import com.pragma.users.application.dto.LoginDTOResponse;

public interface ILoginHandler {
    LoginDTOResponse authenticationUser(LoginDTORequest loginDTORequest);
}
