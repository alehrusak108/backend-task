package com.idea.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIdeaRequest {

    @NotNull
    private Long ownerId;

    @NotBlank
    @Size(max = 50)
    private String category;

    @NotBlank
    private String description;
}
