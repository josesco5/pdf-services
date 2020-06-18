package com.avilapps.pdf_services.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.avilapps.pdf_services.dto.UploadSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;

@Service
public class AWSFileUploader implements FileUploader {
    private static final Logger LOG = LoggerFactory.getLogger(AWSFileUploader.class);
    private AmazonS3 s3Client;

    @Value("${aws.sdk.bucket}")
    private String bucket;


    public AWSFileUploader() {
        s3Client = AmazonS3ClientBuilder.standard().build();
    }

    @Override
    public URL upload(File pdf, UploadSettings settings) {
        String uploadPath = settings.getKey().replace("${filename}", pdf.getName());
        LOG.info(bucket);
        LOG.info(uploadPath);
        s3Client.putObject(bucket, uploadPath, pdf);
        return null;
    }
}
