package com.pragma.users.application.mapper;


import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RolResponseMapper.class})
public interface UserResponseMapper {
    UserDTOResponse userDtoResponse(User user);
}
