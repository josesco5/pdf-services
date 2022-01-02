package com.avilapps.pdf_services.presentation.model

import org.springframework.web.multipart.MultipartFile

class DocumentUploadRequest(
    val destinationPath: String,
    val file: MultipartFile
)
