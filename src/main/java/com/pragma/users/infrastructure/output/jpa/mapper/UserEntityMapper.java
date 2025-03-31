package com.pragma.users.infrastructure.output.jpa.mapper;

import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.User;
import com.pragma.users.infrastructure.output.jpa.entity.RolEntity;
import com.pragma.users.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    @Mapping(source = "rol.idRol", target = "rolEntity.idRol")
    UserEntity toUserEntity(User user);

    @Mapping(source = "rolEntity", target = "rol")
    User toUser(UserEntity userEntity);

    default Rol mapRolEntityToRol(RolEntity rolEntity) {
        if (rolEntity == null) {
            return null;
        }
        return new Rol(rolEntity.getIdRol(), rolEntity.getNameRol(), rolEntity.getDescriptionRol());
    }

    default RolEntity mapRolToRolEntity(Rol rol) {
        if (rol == null) {
            return null;
        }
        RolEntity rolEntity = new RolEntity();
        rolEntity.setIdRol(rol.getIdRol());
        rolEntity.setNameRol(rol.getNameRol());
        rolEntity.setDescriptionRol(rol.getDescriptionRol());
        return rolEntity;
    }

}
