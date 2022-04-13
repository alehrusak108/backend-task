package com.idea.api.dto;

import com.idea.api.model.RoleName;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String photoUrl;
    private String login;
    private LocalDateTime createdAt;
    private Set<RoleName> roles = new HashSet<>();
}
