package com.pragma.users.application.mapper;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = RolRequestMapper.class )
public interface UserRequestMapper {

    @Mapping(target = "rol", ignore = true)
    User toUser(UserDTORequest userDTORequest);
}
