package com.idea.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "amazon")
public class AmazonProperties {

    // Stub properties used here (see application.yml)
    private String serverURL;
    private String awsAccessKeyId;
    private String awsSecretKey;
    private String folder;
    private String bucket;
    private String region;
}
