package com.idea.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AmazonAutoConfiguration {

//    @Bean
//    public S3Client s3Client(AmazonProperties amazonProperties) {
//        return S3Client
//                .builder()
//                .region(Region.of(amazonProperties.getRegion()))
//                .credentialsProvider(() ->
//                        AwsBasicCredentials.create(
//                                amazonProperties.getAwsAccessKeyId(),
//                                amazonProperties.getAwsSecretKey()
//                        ))
//                .build();
//    }
}
