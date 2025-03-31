package com.pragma.users.application.mapper;

import com.pragma.users.application.dto.RolDTOResponse;
import com.pragma.users.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolResponseMapper {
    RolDTOResponse toResponse(Rol rol);

}