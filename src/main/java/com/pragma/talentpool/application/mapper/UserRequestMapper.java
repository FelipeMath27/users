package com.pragma.talentpool.application.mapper;

import com.pragma.talentpool.application.dto.UserDTORequest;
import com.pragma.talentpool.domain.model.Rol;
import com.pragma.talentpool.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    User toUser(UserDTORequest userDTORequest);

    @Mapping(source = "userDTORequest.rol.nameRol"  , target = "nameRol")
    @Mapping(source = "userDTORequest.rol.descriptionRol"  , target = "descriptionRol")
    Rol toRol(UserDTORequest userDTORequest);
}
