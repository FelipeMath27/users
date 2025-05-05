package com.pragma.users.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTOResponse {
    private String token;
    private String nameRol;
}
