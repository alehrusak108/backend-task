package com.idea.api.security;

import com.idea.api.dto.auth.JWTAuthenticationResult;
import com.idea.api.dto.auth.LoginRequest;
import com.idea.api.security.token.JWTHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTHelper jwtHelper;

    public JWTAuthenticationResult authenticateWithJWT(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthentication =
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtHelper.generateJwtToken(authentication);

        return JWTAuthenticationResult.builder()
                .userDetails((UserDetails) authentication.getPrincipal())
                .token(token)
                .build();
    }
}
