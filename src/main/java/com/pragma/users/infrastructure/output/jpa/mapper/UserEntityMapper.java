package com.pragma.users.infrastructure.output.jpa.mapper;

import com.pragma.users.domain.model.User;
import com.pragma.users.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    @Mapping(source = "rol", target = "rolEntity")
    UserEntity toUserEntity(User user);

    @Mapping(source = "rolEntity", target = "rol")
    User toUser(UserEntity userEntity);

    List<User> toUserlist(List<UserEntity> userEntityList);
}
