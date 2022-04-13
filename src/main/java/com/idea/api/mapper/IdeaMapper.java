package com.idea.api.mapper;

import com.idea.api.dto.CreateIdeaRequest;
import com.idea.api.dto.IdeaDto;
import com.idea.api.dto.UpdateIdeaRequest;
import com.idea.api.model.IdeaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(uses = IdeaLikeMapper.class, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IdeaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // this is set by the ORM framework
    IdeaEntity toIdeaEntity(CreateIdeaRequest createIdeaRequest);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // this is set by the ORM framework
    IdeaEntity toIdeaEntity(UpdateIdeaRequest updateIdeaRequest);

    IdeaDto toIdeaDto(IdeaEntity ideaEntity);
    Page<IdeaDto> toIdeaDtoPage(Page<IdeaEntity> ideaEntity);
}