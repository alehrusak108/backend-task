package com.idea.api.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdeaLikeDto {

    private Long ideaId;
    private Long userId;
    private String userPhotoUrl;
    private LocalDateTime createdAt;
}
