package com.idea.api.mapper;

import com.idea.api.dto.IdeaDto;
import com.idea.api.dto.IdeaLikeDto;
import com.idea.api.dto.request.CreateIdeaRequest;
import com.idea.api.dto.request.UpdateIdeaRequest;
import com.idea.api.model.IdeaEntity;
import com.idea.api.model.IdeaLikeEntity;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-14T11:20:37+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class IdeaMapperImpl implements IdeaMapper {

    @Autowired
    private IdeaLikeMapper ideaLikeMapper;

    @Override
    public IdeaEntity toIdeaEntity(CreateIdeaRequest createIdeaRequest) {
        if ( createIdeaRequest == null ) {
            return null;
        }

        IdeaEntity ideaEntity = new IdeaEntity();

        ideaEntity.setOwnerId( createIdeaRequest.getOwnerId() );
        ideaEntity.setCategory( createIdeaRequest.getCategory() );
        ideaEntity.setDescription( createIdeaRequest.getDescription() );

        return ideaEntity;
    }

    @Override
    public IdeaEntity toIdeaEntity(UpdateIdeaRequest updateIdeaRequest) {
        if ( updateIdeaRequest == null ) {
            return null;
        }

        IdeaEntity ideaEntity = new IdeaEntity();

        ideaEntity.setId( updateIdeaRequest.getId() );
        ideaEntity.setOwnerId( updateIdeaRequest.getOwnerId() );
        ideaEntity.setCategory( updateIdeaRequest.getCategory() );
        ideaEntity.setDescription( updateIdeaRequest.getDescription() );

        return ideaEntity;
    }

    @Override
    public IdeaDto toIdeaDto(IdeaEntity ideaEntity) {
        if ( ideaEntity == null ) {
            return null;
        }

        IdeaDto ideaDto = new IdeaDto();

        ideaDto.setId( ideaEntity.getId() );
        ideaDto.setOwnerId( ideaEntity.getOwnerId() );
        ideaDto.setCategory( ideaEntity.getCategory() );
        ideaDto.setDescription( ideaEntity.getDescription() );
        ideaDto.setImageUrl( ideaEntity.getImageUrl() );
        ideaDto.setLikes( ideaLikeEntitySetToIdeaLikeDtoSet( ideaEntity.getLikes() ) );
        ideaDto.setCreatedAt( ideaEntity.getCreatedAt() );
        ideaDto.setUpdatedAt( ideaEntity.getUpdatedAt() );

        return ideaDto;
    }

    protected Set<IdeaLikeDto> ideaLikeEntitySetToIdeaLikeDtoSet(Set<IdeaLikeEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<IdeaLikeDto> set1 = new HashSet<IdeaLikeDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IdeaLikeEntity ideaLikeEntity : set ) {
            set1.add( ideaLikeMapper.toIdeaLikeDto( ideaLikeEntity ) );
        }

        return set1;
    }
}
