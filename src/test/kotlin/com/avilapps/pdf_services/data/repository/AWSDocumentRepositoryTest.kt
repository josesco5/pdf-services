package com.avilapps.pdf_services.data.repository

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectResponse
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import java.io.File

@ExtendWith(MockitoExtension::class)
class AWSDocumentRepositoryTest {

    @Mock
    private lateinit var s3Client: S3Client
    private lateinit var s3Presigner: S3Presigner

    private lateinit var documentRepository: AWSDocumentRepository

    private lateinit var bucketName: String
    private lateinit var destinationPath: String
    private lateinit var file: File
    private lateinit var content: ByteArray

    @BeforeEach
    internal fun setUp() {
        s3Presigner = S3Presigner.builder()
            .region(Region.EU_WEST_1)
            .credentialsProvider { AwsBasicCredentials.create("foo", "bar") }
            .build()
        bucketName = "s3_bucket"
        documentRepository = AWSDocumentRepository(s3Client, s3Presigner, bucketName)

        destinationPath = "/upload/file.pdf"
        file = File("src/test/resources")
        content = "".toByteArray()
    }

    @AfterEach
    internal fun tearDown() {
        s3Presigner.close()
    }

    @Test
    fun shouldUploadDocument() {
        Mockito.`when`(s3Client.putObject(Mockito.any(PutObjectRequest::class.java), Mockito.any(RequestBody::class.java)))
            .thenReturn(PutObjectResponse.builder().build())

        documentRepository.upload(destinationPath, content)
    }

    @Test
    fun shouldBuildPublicUrl() {
        documentRepository.getPublicUrl(destinationPath)
    }
}