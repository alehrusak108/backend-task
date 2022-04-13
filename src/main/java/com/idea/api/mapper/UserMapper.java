package com.idea.api.mapper;

import com.idea.api.dto.UserDto;
import com.idea.api.model.Role;
import com.idea.api.model.RoleName;
import com.idea.api.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    UserDto toUserDto(UserEntity userEntity);

    default RoleName toRoleName(Role role) {
        return role.getName();
    }
}
