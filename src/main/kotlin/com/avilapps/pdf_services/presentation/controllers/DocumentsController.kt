package com.avilapps.pdf_services.presentation.controllers

import com.avilapps.pdf_services.domain.service.DocumentService
import com.avilapps.pdf_services.presentation.model.DocumentUploadRequest
import com.avilapps.pdf_services.presentation.model.DocumentUploadResponse
import com.avilapps.pdf_services.utils.FileUtils
import org.springframework.web.bind.annotation.RestController

@RestController
class DocumentsController(private val documentService: DocumentService, private val fileUtils: FileUtils): DocumentsApi {

    override fun upload(request: DocumentUploadRequest): DocumentUploadResponse {
        val file = fileUtils.receiveRemotePDF(request.file)
        documentService.upload(request.destinationPath, file)
        val response = DocumentUploadResponse()
        response.url = "http://localhost:8080"
        return response
    }
}