package com.avilapps.pdf_services.data.repository

import com.avilapps.pdf_services.domain.repository.DocumentRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest
import java.net.URL
import java.time.Duration

@Repository
class AWSDocumentRepository(private val s3Client: S3Client,
                            private val s3Presigner: S3Presigner,
                            @Value("\${aws.sdk.bucket}") private val bucketName: String
                            ): DocumentRepository {

    override fun upload(destinationPath: String, content: ByteArray) {
        val objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(destinationPath)
            .build()
        s3Client.putObject(objectRequest, RequestBody.fromBytes(content))

    }

    override fun getPublicUrl(filePath: String): URL {
        val getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(filePath)
            .build()

        val getObjectPresignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(10))
            .getObjectRequest(getObjectRequest)
            .build()

        val presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest)

        return presignedGetObjectRequest.url()
    }

    override fun download(filePath: String): ByteArray {
        val getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(filePath)
            .build()
        val fullObject = s3Client.getObject(getObjectRequest)

        return fullObject.readBytes()
    }
}