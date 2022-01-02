package com.avilapps.pdf_services.data.repository

import com.amazonaws.services.s3.AmazonS3
import com.avilapps.pdf_services.domain.repository.DocumentRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class AWSDocumentRepository(private val s3Client: AmazonS3,  @Value("\${aws.sdk.bucket}") private val bucketName: String): DocumentRepository {

    override fun upload(destinationPath: String, content: File) {
        s3Client.putObject(bucketName, destinationPath, content)
    }
}