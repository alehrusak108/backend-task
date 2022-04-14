package com.idea.api.service;

import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.util.IOUtils;
import com.idea.api.config.AmazonProperties;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * This is a stub service put here just for an example of how AWS S3 image upload can work.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AmazonS3Service {

    private final AmazonProperties amazonProperties;
    private final S3Client s3Client;

    // prometheus metrics must be collected here
    public String uploadImage(String domainObjectId, byte[] imageBytes, ContentType contentType) {
        String imageKey = generateImageKey(domainObjectId);
        doUploadImage(imageBytes, imageKey, contentType);
        return generateImageLink(imageKey);
    }

    public void doUploadImage(byte[] imageBytes, String imageKey, ContentType contentType) {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(amazonProperties.getBucket())
                        .contentType(contentType.toString())
                        .contentLength((long) imageBytes.length)
                        .acl(ObjectCannedACL.PUBLIC_READ)
                        .key(imageKey)
                        .build(),
                RequestBody.fromBytes(imageBytes)
        );
    }

    public byte[] downloadIdeaImage(String pathToIdeaImage) throws IOException {
        try (var inputStream = new URL(pathToIdeaImage).openStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }

    public void deleteFileByUrl(String url) {
        deleteFile(new AmazonS3URI(url).getKey());
    }

    public void deleteFile(String fileKey) {
        s3Client.deleteObject(DeleteObjectRequest
                .builder()
                .bucket(amazonProperties.getBucket())
                .key(fileKey)
                .build());
    }

    public String generateImageKey(String ideaId) {
        String uuid = UUID.randomUUID().toString();
        return String.format("%s/%s/%s.jpg", amazonProperties.getFolder(), ideaId, uuid);
    }

    public String generateImageLink(String imageKey) {
        return String.format("%s/%s/%s", amazonProperties.getServerURL(), amazonProperties.getBucket(), imageKey);
    }
}
