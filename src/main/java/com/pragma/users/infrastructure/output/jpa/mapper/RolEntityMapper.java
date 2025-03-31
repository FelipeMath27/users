package com.pragma.users.infrastructure.output.jpa.mapper;

import com.pragma.users.domain.model.Rol;
import com.pragma.users.infrastructure.output.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface RolEntityMapper {
    RolEntity toRolEntity(Rol ro);

    Rol toRol(RolEntity rolEntity);
}
