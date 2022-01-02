package com.avilapps.pdf_services.domain.service

import com.avilapps.pdf_services.domain.repository.DocumentRepository
import org.springframework.stereotype.Service
import java.io.File

@Service
class DocumentService(private val documentRepository: DocumentRepository) {

    fun upload(destinationPath: String, content: File): String {
        documentRepository.upload(destinationPath, content)
        return ""
    }
}