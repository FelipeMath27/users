package com.pragma.users.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTORequest {
    private String email;
    private String password;
}
