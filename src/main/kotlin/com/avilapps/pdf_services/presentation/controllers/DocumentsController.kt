package com.avilapps.pdf_services.presentation.controllers

import com.avilapps.pdf_services.api.model.UploadDocumentResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DocumentsController {

    @PostMapping("/upload")
    fun upload(): ResponseEntity<UploadDocumentResponse> {
        return ResponseEntity.ok(null)
    }
}