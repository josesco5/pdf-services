package com.avilapps.pdf_services.domain.service

import com.avilapps.pdf_services.domain.repository.DocumentRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.io.File

@ExtendWith(MockitoExtension::class)
class DocumentServiceTest {

    @Mock
    private lateinit var documentRepository: DocumentRepository

    @InjectMocks
    private lateinit var documentService: DocumentService

    private lateinit var destinationPath: String
    private lateinit var file: File

    @BeforeEach
    internal fun setUp() {
        destinationPath = ""
        file = File("src/test/resources")
    }

    @Test
    fun shouldUploadDocument() {
        Mockito.doNothing().`when`(documentRepository).upload(destinationPath, file)

        documentService.upload(destinationPath, file)
    }
}