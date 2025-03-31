package com.pragma.users.infrastructure.output.jpa.mapper;

import com.pragma.users.domain.model.User;
import com.pragma.users.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = RolEntityMapper.class )
public interface UserEntityMapper {
    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);

}
