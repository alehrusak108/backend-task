package com.idea.api.mapper;

import com.idea.api.dto.IdeaLikeDto;
import com.idea.api.model.IdeaLikeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IdeaLikeMapper {

    @Mapping(target = "id", ignore = true)
    IdeaLikeDto toIdeaLikeDto(IdeaLikeEntity ideaLikeEntity);
}
