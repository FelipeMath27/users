package com.pragma.users.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolDTOResponse {
    private Long idRol;
    private String nameRol;
    private String descriptionRol;
}
