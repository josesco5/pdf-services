package com.avilapps.pdf_services.domain.service

import com.avilapps.pdf_services.domain.repository.DocumentRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.io.File
import java.net.URL

@ExtendWith(MockitoExtension::class)
class DocumentServiceTest {

    @Mock
    private lateinit var documentRepository: DocumentRepository

    @Mock
    private lateinit var foliationService: FoliationService

    @InjectMocks
    private lateinit var documentService: DocumentService

    private lateinit var destinationPath: String
    private lateinit var file: File
    private lateinit var content: ByteArray

    @BeforeEach
    internal fun setUp() {
        destinationPath = ""
        file = File("src/test/resources")
        content = "".toByteArray()
    }

    @Test
    fun shouldUploadDocument() {
        Mockito.doNothing().`when`(documentRepository).upload(destinationPath, content)
        Mockito.`when`(documentRepository.getPublicUrl(anyString())).thenReturn(URL("http://localhost:8080"))

        documentService.upload(destinationPath, content)
    }
}