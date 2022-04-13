package com.idea.api.dto;


import com.idea.api.model.IdeaLikeEntity;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdeaDto {

    private Long id;
    private Long ownerId;
    private String category;
    private String description;
    private String imageUrl;
    private Set<IdeaLikeDto> likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
