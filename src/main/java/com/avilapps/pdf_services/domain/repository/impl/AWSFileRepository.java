package com.avilapps.pdf_services.domain.repository.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.ContentFile;
import com.avilapps.pdf_services.domain.repository.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;

@Service
public class AWSFileRepository implements FileUploader {
    private static final Logger LOG = LoggerFactory.getLogger(AWSFileRepository.class);
    private AmazonS3 s3Client;

    @Value("${aws.sdk.bucket}")
    private String bucket;

    public AWSFileRepository(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String upload(ContentFile contentFile) {
        try {
            String uploadPath = contentFile.getUrl();
            LOG.info(bucket);
            LOG.info(uploadPath);
            s3Client.putObject(bucket, uploadPath, contentFile.getContent());

            return buildFileUrl(contentFile);
        } catch (Exception exception) {
            LOG.error("Error when uploading file to S3", exception);
            throw new ServiceException(exception);
        }
    }

    private String buildFileUrl(ContentFile contentFile) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, contentFile.getUrl())
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
