package com.pragma.users.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "PRG_TBL_ROLES")
@SequenceGenerator(name = "rol_seq", sequenceName = "prg_tbl_roles_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_seq")
    @Column(name = "idRol")
    private Long idRol;

    @NotBlank(message = "Name rol is required")
    @Column(name = "nameRol")
    private String nameRol;

    @NotBlank(message = "Description rol is required")
    @Column(name = "descriptionRol")
    private String descriptionRol;
}
