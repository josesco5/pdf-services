package com.avilapps.pdf_services.data.repository

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.PutObjectResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.io.File

@ExtendWith(MockitoExtension::class)
class AWSDocumentRepositoryTest {

    @Mock
    private lateinit var s3Client: AmazonS3

    private lateinit var documentRepository: AWSDocumentRepository

    private lateinit var bucketName: String
    private lateinit var destinationPath: String
    private lateinit var file: File

    @BeforeEach
    internal fun setUp() {
        bucketName = "s3_bucket"
        documentRepository = AWSDocumentRepository(s3Client, bucketName)

        destinationPath = ""
        file = File("src/test/resources")
    }

    @Test
    fun shouldUploadDocument() {
        Mockito.`when`(s3Client.putObject(bucketName, destinationPath, file)).thenReturn(PutObjectResult())

        documentRepository.upload(destinationPath, file)
    }
}