package com.avilapps.pdf_services.common.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner

@Configuration
class AWSConfig {

    @Bean
    fun getS3Client(): S3Client {
        return S3Client.create()
    }

    @Bean
    fun getS3Presigner(): S3Presigner {
        return S3Presigner.create()
    }
}