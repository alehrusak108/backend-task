package com.idea.api.web.rest;

import com.idea.api.dto.CreateIdeaRequest;
import com.idea.api.dto.IdeaDto;
import com.idea.api.dto.LikeIdeaRequest;
import com.idea.api.dto.UpdateIdeaRequest;
import com.idea.api.model.IdeaEntity;
import com.idea.api.model.IdeaLikeEntity;
import com.idea.api.service.IdeaService;
import com.idea.api.web.util.MultipartFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/idea")
public class IdeaController {

    private final IdeaService ideaService;

    @GetMapping("/{ideaId}")
    public IdeaDto retrieveById(@PathVariable("ideaId") Long ideaId) {
        log.info("REST request to retrieve Idea by ID {}", ideaId);
        return ideaService.findById(ideaId);
    }

    @GetMapping
    public Page<IdeaDto> retrieveByCategory(@RequestParam("category") String category,
                                            @RequestParam int page,
                                            @RequestParam int pageSize) {
        log.info("REST request to retrieve Ideas by Category {}", category);
        return ideaService.findByCategory(category, PageRequest.of(page, pageSize));
    }

    @PostMapping
    public ResponseEntity<IdeaEntity> createIdea(@RequestBody CreateIdeaRequest createIdeaRequest) {
        // validator
        log.info("REST request to create an Idea: {}", createIdeaRequest);
        IdeaEntity createdIdea = ideaService.createIdea(createIdeaRequest);
        return ResponseEntity.ok(createdIdea);
    }

    @PutMapping(value = "/{ideaId}")
    public ResponseEntity<IdeaEntity> updateIdea(@RequestBody UpdateIdeaRequest updateIdeaRequest) {
        // validator
        log.info("REST request to update an Idea: {}", updateIdeaRequest);
        IdeaEntity updatedIdea = ideaService.updateIdea(updateIdeaRequest);
        return ResponseEntity.ok(updatedIdea);
    }

    @PostMapping(value = "/{ideaId}/like")
    public ResponseEntity<IdeaLikeEntity> likeIdea(@PathVariable("ideaId") Long ideaId,
                                                   @RequestParam("likeInitiatorId") Long likeInitiatorId) {
        // validator
        log.info("REST request to Like an Idea. Idea {}, Like Initiator {}", ideaId, likeInitiatorId);
        IdeaLikeEntity ideaLikeEntity = ideaService.saveIdeaLike(ideaId, likeInitiatorId);
        return ResponseEntity.ok(ideaLikeEntity);
    }

    @PostMapping(value = "/{ideaId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<IdeaEntity> uploadIdeaImage(@PathVariable("ideaId") Long ideaId,
                                                      @RequestPart("image") MultipartFile image) {
        log.info("REST request to upload Image (size = {}) for Idea {}", image.getSize(), ideaId);

        // Content type may be resolved dynamically by using specific utilities to verify that image matches
        ContentType contentType = ContentType.IMAGE_JPEG;
        byte[] imageBytes = MultipartFileUtils.retrieveMultipartFileBytes(image);
        IdeaEntity updatedIdea = ideaService.uploadAndAssociateImageWithIdea(ideaId, imageBytes, contentType);
        return ResponseEntity.ok(updatedIdea);
    }
}
