package com.idea.api.mapper;

import com.idea.api.dto.IdeaLikeDto;
import com.idea.api.model.IdeaLikeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IdeaLikeMapper {

    @Mapping(target = "ideaId", source = "likedIdea.id")
    @Mapping(target = "userId", source = "userLiked.id")
    @Mapping(target = "userPhotoUrl", source = "userLiked.photoUrl")
    IdeaLikeDto toIdeaLikeDto(IdeaLikeEntity ideaLikeEntity);
}
