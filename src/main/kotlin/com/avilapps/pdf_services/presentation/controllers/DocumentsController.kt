package com.avilapps.pdf_services.presentation.controllers

import com.avilapps.pdf_services.domain.service.DocumentService
import com.avilapps.pdf_services.presentation.model.DocumentFoliateRequest
import com.avilapps.pdf_services.presentation.model.DocumentFoliateResponse
import com.avilapps.pdf_services.presentation.model.DocumentUploadRequest
import com.avilapps.pdf_services.presentation.model.DocumentUploadResponse
import org.springframework.web.bind.annotation.RestController

@RestController
class DocumentsController(private val documentService: DocumentService): DocumentsApi {

    override fun upload(request: DocumentUploadRequest): DocumentUploadResponse {
        val fileUrl = documentService.upload(request.destinationPath, request.file.bytes)
        val response = DocumentUploadResponse()
        response.url = fileUrl
        return response
    }

    override fun foliate(request: DocumentFoliateRequest): DocumentFoliateResponse {
        val fileUrl = documentService.foliate(request.originPath, request.initialFolio, request.destinationPath)
        return DocumentFoliateResponse(fileUrl)
    }
}