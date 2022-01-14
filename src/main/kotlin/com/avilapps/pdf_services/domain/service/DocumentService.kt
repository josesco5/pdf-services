package com.avilapps.pdf_services.domain.service

import com.avilapps.pdf_services.domain.repository.DocumentRepository
import org.springframework.stereotype.Service

@Service
class DocumentService(private val documentRepository: DocumentRepository, private val foliationService: FoliationService) {

    fun upload(destinationPath: String, content: ByteArray): String {
        documentRepository.upload(destinationPath, content)
        return documentRepository.getPublicUrl(destinationPath).toString()
    }

    fun foliate(originPath: String, initialFolio: Int, destinationPath: String): String {
        val originalContent = documentRepository.download(originPath)
        val foliatedContent = foliationService.foliate(originPath, originalContent, initialFolio)
        documentRepository.upload(destinationPath, foliatedContent)
        return documentRepository.getPublicUrl(destinationPath).toString()
    }
}