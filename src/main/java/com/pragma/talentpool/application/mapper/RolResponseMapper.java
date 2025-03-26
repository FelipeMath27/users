package com.pragma.talentpool.application.mapper;

import com.pragma.talentpool.application.dto.RolDTOResponse;
import com.pragma.talentpool.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolResponseMapper {
    RolDTOResponse toResponse(Rol rol);

    List<RolDTOResponse> toResponseList(List<Rol> roles);
}