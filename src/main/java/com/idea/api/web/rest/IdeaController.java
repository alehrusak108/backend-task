package com.idea.api.web.rest;

import com.idea.api.dto.CreateIdeaRequest;
import com.idea.api.dto.IdeaDto;
import com.idea.api.dto.IdeaLikeDto;
import com.idea.api.dto.UpdateIdeaRequest;
import com.idea.api.model.IdeaEntity;
import com.idea.api.service.IdeaService;
import com.idea.api.util.MultipartFileUtils;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
                                            @RequestParam @Min(0) int page,
                                            @RequestParam @Max(20) int pageSize) {

        log.info("REST request to retrieve Ideas by Category {}", category);
        return ideaService.findByCategory(category, PageRequest.of(page, pageSize));
    }

    @PostMapping
    public ResponseEntity<IdeaEntity> createIdea(@Valid @RequestBody CreateIdeaRequest createIdeaRequest) {
        log.info("REST request to create an Idea: {}", createIdeaRequest);
        IdeaEntity createdIdea = ideaService.createIdea(createIdeaRequest);
        return ResponseEntity.ok(createdIdea);
    }

    @PutMapping(value = "/{ideaId}")
    public ResponseEntity<IdeaEntity> updateIdea(@Valid @RequestBody UpdateIdeaRequest updateIdeaRequest) {
        log.info("REST request to update an Idea: {}", updateIdeaRequest);
        IdeaEntity updatedIdea = ideaService.updateIdea(updateIdeaRequest);
        return ResponseEntity.ok(updatedIdea);
    }

    @PostMapping(value = "/{ideaId}/like")
    public ResponseEntity<IdeaLikeDto> likeIdea(@PathVariable("ideaId") Long ideaId,
                                                @RequestParam("likeInitiatorId") Long likeInitiatorId) {

        log.info("REST request to Like an Idea. Idea {}, Like Initiator {}", ideaId, likeInitiatorId);
        IdeaLikeDto ideaLikeDto = ideaService.saveIdeaLike(ideaId, likeInitiatorId);
        return ResponseEntity.ok(ideaLikeDto);
    }

    @PostMapping(value = "/{ideaId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<IdeaEntity> uploadIdeaImage(@PathVariable("ideaId") Long ideaId,
                                                      @RequestPart("image") MultipartFile image) {

        log.info("REST request to upload Image (size = {}) for Idea {}", image.getSize(), ideaId);
        // Content type may be resolved dynamically by using specific utilities
        // to verify that image has acceptable content type
        ContentType contentType = ContentType.IMAGE_JPEG;
        byte[] imageBytes = MultipartFileUtils.retrieveMultipartFileBytes(image);
        IdeaEntity updatedIdea = ideaService.uploadAndAssociateImageWithIdea(ideaId, imageBytes, contentType);
        return ResponseEntity.ok(updatedIdea);
    }
}
