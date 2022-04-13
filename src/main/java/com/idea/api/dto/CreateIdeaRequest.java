package com.idea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIdeaRequest {

    private Long ownerId;
    private String category;
    private String description;
    private String imageUrl;
}
