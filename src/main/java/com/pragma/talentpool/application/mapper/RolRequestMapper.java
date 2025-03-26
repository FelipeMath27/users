package com.pragma.talentpool.application.mapper;

import com.pragma.talentpool.application.dto.RolDTORequest;
import com.pragma.talentpool.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface RolRequestMapper {
    Rol toRol(RolDTORequest rolDTORequest);
}
