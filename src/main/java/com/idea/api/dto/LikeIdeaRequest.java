package com.idea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeIdeaRequest {

    private Long ideaId;
    private Long userId;
}
