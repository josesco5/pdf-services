package com.avilapps.pdf_services.common.configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public AmazonS3 getAmazonS3Client() {
        return AmazonS3ClientBuilder.standard().build();
    }

}
