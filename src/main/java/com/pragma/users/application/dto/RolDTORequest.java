package com.pragma.users.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RolDTORequest {
    private String nameRol;
    private String descriptionRol;
}
