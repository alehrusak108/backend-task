package com.idea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIdeaRequest {

    private Long id;
    private Long ownerId;
    private String category;
    private String description;
    private String imageUrl;
}
