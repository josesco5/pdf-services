package com.avilapps.pdf_services.domain.service

import org.apache.pdfbox.pdmodel.PDDocument
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Paths

@Service
class FoliationService {
    private val tempDirectory = File(System.getProperty("java.io.tmpdir"))

    fun foliate(originFilePath: String, content: ByteArray, initialFolio: Int): ByteArray {

        val document = PDDocument.load(content)
        val fileName = Paths.get(originFilePath).fileName.toString()

        // ToDo: Foliate document

        val outputFile = File(tempDirectory, fileName)
        document.save(outputFile)
        document.close()

        val outputContent = outputFile.readBytes()
        outputFile.deleteOnExit()

        return outputContent
    }
}