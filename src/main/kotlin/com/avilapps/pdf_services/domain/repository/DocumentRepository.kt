package com.avilapps.pdf_services.domain.repository

import java.net.URL

interface DocumentRepository {

    fun upload(destinationPath: String, content: ByteArray)

    fun getPublicUrl(filePath: String): URL
}