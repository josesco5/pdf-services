package com.avilapps.pdf_services.presentation.controllers

import com.avilapps.pdf_services.domain.service.DocumentService
import com.avilapps.pdf_services.presentation.model.DocumentFoliateRequest
import com.avilapps.pdf_services.utils.FileUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.multipart.MultipartFile
import java.io.File

@WebMvcTest(controllers = [DocumentsController::class])
class DocumentsControllerTest {

    @MockBean
    private lateinit var documentService: DocumentService

    @MockBean
    private lateinit var fileUtils: FileUtils

    @Autowired
    private lateinit var mockMvc: MockMvc

    private lateinit var sentFile: MockMultipartFile
    private lateinit var file: File
    private lateinit var destinationPath: String
    private lateinit var content: ByteArray

    @BeforeEach
    fun setup() {
        content = "Test content".toByteArray()
        sentFile = MockMultipartFile(
            "file", "test.pdf", MediaType.APPLICATION_PDF_VALUE,
            content
        )

        destinationPath = ""
        file = File("src/test/resources")
    }

    @Test
    fun `should return OK response when uploading a file`() {
        Mockito.`when`(fileUtils.receiveRemotePDF(any(MultipartFile::class.java))).thenReturn(file)
        Mockito.`when`(documentService.upload(destinationPath, content)).thenReturn("http://localhost/pdf")

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/documents/upload")
            .file(sentFile)
            .param("destinationPath", destinationPath))
            .andExpect(status().isOk)
    }

    @Test
    fun `should return OK response when foliating a file`() {
        val foliateRequest = DocumentFoliateRequest("", 1, "")
        Mockito.`when`(documentService.foliate(anyString(), anyInt(), anyString())).thenReturn("http://localhost/pdf")

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/documents/foliate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(foliateRequest)))
            .andExpect(status().isOk)

    }
}