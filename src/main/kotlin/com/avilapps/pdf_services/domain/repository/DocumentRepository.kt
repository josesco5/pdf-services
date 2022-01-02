package com.avilapps.pdf_services.domain.repository

import java.io.File

interface DocumentRepository {

    fun upload(destinationPath: String, content: File)
}