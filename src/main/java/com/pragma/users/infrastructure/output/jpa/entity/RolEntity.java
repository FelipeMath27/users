package com.pragma.users.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "PRG_TBL_ROLES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idRol;

    @NotBlank(message = "Name rol is required")
    private String nameRol;

    @NotBlank(message = "Description rol is required")
    private String descriptionRol;
}
