package com.avilapps.pdf_services.domain.service

import com.avilapps.pdf_services.domain.repository.DocumentRepository
import org.springframework.stereotype.Service

@Service
class DocumentService(private val documentRepository: DocumentRepository) {

    fun upload(destinationPath: String, content: ByteArray): String {
        documentRepository.upload(destinationPath, content)
        return documentRepository.getPublicUrl(destinationPath).toString()
    }
}