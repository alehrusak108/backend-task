package com.idea.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIdeaRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long ownerId;

    @NotBlank
    @Size(max = 100)
    private String category;

    @NotBlank
    private String description;
}
