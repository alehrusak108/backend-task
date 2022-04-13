package com.idea.api.service;

import com.idea.api.dto.CreateIdeaRequest;
import com.idea.api.dto.IdeaDto;
import com.idea.api.dto.UpdateIdeaRequest;
import com.idea.api.mapper.IdeaMapper;
import com.idea.api.model.IdeaEntity;
import com.idea.api.model.IdeaLikeEntity;
import com.idea.api.repository.IdeaLikeRepository;
import com.idea.api.repository.IdeaRepository;
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
    private final IdeaLikeRepository ideaLikeRepository;

    private final AmazonS3Service amazonS3Service;

    @Transactional(readOnly = true)
    public IdeaDto findById(Long ideaId) {
        IdeaEntity ideaEntity = ideaRepository.findByIdThrows(ideaId);
        return ideaMapper.toIdeaDto(ideaEntity);
    }

    @Transactional(readOnly = true)
    public Page<IdeaDto> findByCategory(String category, Pageable pageable) {
        Page<IdeaEntity> ideaEntities = ideaRepository.findByCategoryLikeOrderByUpdatedAtDesc(category, pageable);
        return ideaMapper.toIdeaDtoPage(ideaEntities);
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
    public IdeaLikeEntity saveIdeaLike(Long ideaId, Long likeInitiatorId) {
        // validate
        IdeaLikeEntity ideaLikeEntity = new IdeaLikeEntity();
        ideaLikeEntity.setIdeaId(ideaId);
        ideaLikeEntity.setUserId(likeInitiatorId);
        return ideaLikeRepository.save(ideaLikeEntity);
    }

    @Transactional
    public IdeaEntity uploadAndAssociateImageWithIdea(Long ideaId, byte[] imageBytes, ContentType contentType) {
        String imageUrl = amazonS3Service.uploadImage(ideaId.toString(), imageBytes, contentType);
        IdeaEntity ideaEntity = ideaRepository.findByIdThrows(ideaId);
        ideaEntity.setImageUrl(imageUrl);
        return ideaRepository.save(ideaEntity);
    }
}