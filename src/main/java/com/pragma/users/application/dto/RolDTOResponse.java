package com.pragma.users.application.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolDTOResponse {
    private String name_rol;
    private String description_rol;
}
