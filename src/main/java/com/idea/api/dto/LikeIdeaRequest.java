package com.idea.api.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeIdeaRequest {

    @NotNull
    private Long ideaId;

    @NotNull
    private Long userId;
}
