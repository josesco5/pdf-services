package com.avilapps.pdf_services.presentation.controllers

import com.avilapps.pdf_services.presentation.model.DocumentUploadRequest
import com.avilapps.pdf_services.presentation.model.DocumentUploadResponse
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1/documents")
interface DocumentsApi {
    @PostMapping("/upload")
    fun upload(@ModelAttribute request: DocumentUploadRequest): DocumentUploadResponse
}