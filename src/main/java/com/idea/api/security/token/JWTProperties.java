package com.idea.api.security.token;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("security.jwt")
public class JWTProperties {

    private String encodedSecret;
    private String issuer;
    private int expirationSeconds;
}
