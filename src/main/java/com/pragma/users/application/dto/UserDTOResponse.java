package com.pragma.users.application.dto;

import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeDocumentEnum;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOResponse {
    private String name_user;
    private String lastName_user;
    private TypeDocumentEnum typeDocument_user;
    private String document_user;
    private String phoneNumber_user;
    private LocalDate dateBirth_user;
    private String email;
    private Rol rol;
}
