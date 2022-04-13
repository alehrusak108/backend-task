package com.idea.api.mapper;

import com.idea.api.dto.UserDto;
import com.idea.api.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toUserDto(UserEntity userEntity);
}
