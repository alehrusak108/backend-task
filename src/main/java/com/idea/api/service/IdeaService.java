package com.idea.api.service;

import com.idea.api.dto.request.CreateIdeaRequest;
import com.idea.api.dto.IdeaDto;
import com.idea.api.dto.IdeaLikeDto;
import com.idea.api.dto.request.UpdateIdeaRequest;
import com.idea.api.mapper.IdeaLikeMapper;
import com.idea.api.mapper.IdeaMapper;
import com.idea.api.model.IdeaEntity;
import com.idea.api.model.IdeaLikeEntity;
import com.idea.api.model.UserEntity;
import com.idea.api.repository.IdeaLikeRepository;
import com.idea.api.repository.IdeaRepository;
import com.idea.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdeaService {

    private final IdeaMapper ideaMapper;
    private final IdeaRepository ideaRepository;

    private final IdeaLikeMapper ideaLikeMapper;
    private final IdeaLikeRepository ideaLikeRepository;

    private final UserRepository userRepository;

    private final AmazonS3Service amazonS3Service;

    @Transactional(readOnly = true)
    public IdeaDto findById(Long ideaId) {
        IdeaEntity ideaEntity = ideaRepository.findByIdThrows(ideaId);
        return ideaMapper.toIdeaDto(ideaEntity);
    }

    @Transactional(readOnly = true)
    public Page<IdeaDto> findByCategory(String category, Pageable pageable) {
        return ideaRepository.findByCategoryContainsIgnoreCaseOrderByUpdatedAtDesc(category, pageable)
                .map(ideaMapper::toIdeaDto);
    }

    @Transactional
    public IdeaEntity createIdea(CreateIdeaRequest createIdeaRequest) {
        IdeaEntity ideaEntity = ideaMapper.toIdeaEntity(createIdeaRequest);
        return ideaRepository.save(ideaEntity);
    }

    @Transactional
    public IdeaEntity updateIdea(UpdateIdeaRequest updateIdeaRequest) {
        IdeaEntity ideaEntity = ideaMapper.toIdeaEntity(updateIdeaRequest);
        return ideaRepository.save(ideaEntity);
    }

    @Transactional
    public IdeaLikeDto saveIdeaLike(Long ideaId, Long likeInitiatorId) {
        IdeaEntity ideaEntity = ideaRepository.findByIdThrows(ideaId);
        UserEntity userEntity = userRepository.findByIdThrows(likeInitiatorId);

        IdeaLikeEntity ideaLikeEntity = new IdeaLikeEntity();
        ideaLikeEntity.setLikedIdea(ideaEntity);
        ideaLikeEntity.setUserLiked(userEntity);
        IdeaLikeEntity savedIdeaLike = ideaLikeRepository.save(ideaLikeEntity);

        return ideaLikeMapper.toIdeaLikeDto(savedIdeaLike);
    }

    @Transactional
    public IdeaEntity uploadAndAssociateImageWithIdea(Long ideaId, byte[] imageBytes, ContentType contentType) {
        String imageUrl = amazonS3Service.uploadImage(ideaId.toString(), imageBytes, contentType);
        IdeaEntity ideaEntity = ideaRepository.findByIdThrows(ideaId);
        ideaEntity.setImageUrl(imageUrl);
        return ideaRepository.save(ideaEntity);
    }
}
