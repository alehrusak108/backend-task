package com.idea.api.mapper;

import com.idea.api.dto.request.CreateIdeaRequest;
import com.idea.api.dto.IdeaDto;
import com.idea.api.dto.request.UpdateIdeaRequest;
import com.idea.api.model.IdeaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = IdeaLikeMapper.class, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IdeaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // this is set by the ORM framework
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    IdeaEntity toIdeaEntity(CreateIdeaRequest createIdeaRequest);

    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // this is set by the ORM framework
    @Mapping(target = "imageUrl", ignore = true)
    IdeaEntity toIdeaEntity(UpdateIdeaRequest updateIdeaRequest);

    IdeaDto toIdeaDto(IdeaEntity ideaEntity);
}
