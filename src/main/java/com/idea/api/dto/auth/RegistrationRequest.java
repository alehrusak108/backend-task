package com.idea.api.dto.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationRequest {

    private String username;
    private String login;
    private String password;
}
