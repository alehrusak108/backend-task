package com.idea.api.mapper;

import com.idea.api.dto.IdeaLikeDto;
import com.idea.api.model.IdeaEntity;
import com.idea.api.model.IdeaLikeEntity;
import com.idea.api.model.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-14T11:20:37+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class IdeaLikeMapperImpl implements IdeaLikeMapper {

    @Override
    public IdeaLikeDto toIdeaLikeDto(IdeaLikeEntity ideaLikeEntity) {
        if ( ideaLikeEntity == null ) {
            return null;
        }

        IdeaLikeDto ideaLikeDto = new IdeaLikeDto();

        ideaLikeDto.setIdeaId( ideaLikeEntityLikedIdeaId( ideaLikeEntity ) );
        ideaLikeDto.setUserId( ideaLikeEntityUserLikedId( ideaLikeEntity ) );
        ideaLikeDto.setUserPhotoUrl( ideaLikeEntityUserLikedPhotoUrl( ideaLikeEntity ) );
        ideaLikeDto.setCreatedAt( ideaLikeEntity.getCreatedAt() );

        return ideaLikeDto;
    }

    private Long ideaLikeEntityLikedIdeaId(IdeaLikeEntity ideaLikeEntity) {
        if ( ideaLikeEntity == null ) {
            return null;
        }
        IdeaEntity likedIdea = ideaLikeEntity.getLikedIdea();
        if ( likedIdea == null ) {
            return null;
        }
        Long id = likedIdea.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long ideaLikeEntityUserLikedId(IdeaLikeEntity ideaLikeEntity) {
        if ( ideaLikeEntity == null ) {
            return null;
        }
        UserEntity userLiked = ideaLikeEntity.getUserLiked();
        if ( userLiked == null ) {
            return null;
        }
        Long id = userLiked.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String ideaLikeEntityUserLikedPhotoUrl(IdeaLikeEntity ideaLikeEntity) {
        if ( ideaLikeEntity == null ) {
            return null;
        }
        UserEntity userLiked = ideaLikeEntity.getUserLiked();
        if ( userLiked == null ) {
            return null;
        }
        String photoUrl = userLiked.getPhotoUrl();
        if ( photoUrl == null ) {
            return null;
        }
        return photoUrl;
    }
}
