package com.avilapps.pdf_services.domain.service

import com.avilapps.pdf_services.utils.NumberToWords
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDFont
import org.apache.pdfbox.pdmodel.font.PDType0Font
import org.apache.pdfbox.util.Matrix
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Paths

@Service
class FoliationService {
    private val tempDirectory = File(System.getProperty("java.io.tmpdir"))
    private val fontSize = 10

    fun foliate(originFilePath: String, content: ByteArray, initialFolio: Int): ByteArray {

        val document = PDDocument.load(content)
        val fileName = Paths.get(originFilePath).fileName.toString()

        // ToDo: Foliate document
        foliateDocumentPages(document)

        val outputFile = File(tempDirectory, fileName)
        document.save(outputFile)
        document.close()

        val outputContent = outputFile.readBytes()
        outputFile.deleteOnExit()

        return outputContent
    }

    private fun foliateDocumentPages(document: PDDocument) {
        val fontResource = ClassPathResource("static/fonts/Roboto-Regular.ttf")
        val font = PDType0Font.load(document, fontResource.inputStream)
        val totalPages = document.numberOfPages

        for (i in 1..totalPages) {
            addFolioToPage(document, i, font)
        }
    }

    private fun addFolioToPage(document: PDDocument, pageNumber: Int, font: PDFont) {
        val page = document.getPage(pageNumber - 1)
        val contentStream = PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)

        val pageWidth = page.mediaBox.width
        val pageHeight = page.mediaBox.height
        val pageRotation = page.rotation

        val firstLineText = "Fojas $pageNumber"
        val writtenFolio = NumberToWords.convert(pageNumber)

        val firstLineTextWidth = calculateTextWidth(firstLineText, font)
        val writtenFolioWidth = calculateTextWidth(writtenFolio, font)

        val firstLinePositionX: Float
        val firstLinePositionY: Float
        val writtenFolioPositionX: Float
        val writtenFolioPositionY: Float

        when (pageRotation) {
            90 -> {
                firstLinePositionX = 40f
                firstLinePositionY = pageHeight - firstLineTextWidth - 40

                writtenFolioPositionX = firstLinePositionX + 12.5f
                writtenFolioPositionY = pageHeight - writtenFolioWidth - 40
            }
            180 -> {
                firstLinePositionX = firstLineTextWidth + 40
                firstLinePositionY = 40f

                writtenFolioPositionX = writtenFolioWidth + 40
                writtenFolioPositionY = 40 + 12.5f
            }
            270 -> {
                firstLinePositionX = pageWidth - 40
                firstLinePositionY = firstLineTextWidth + 40

                writtenFolioPositionX = firstLinePositionX - 12.5f
                writtenFolioPositionY = writtenFolioWidth + 40
            }
            else -> {
                firstLinePositionX = pageWidth - firstLineTextWidth - 40
                firstLinePositionY = pageHeight - 40

                writtenFolioPositionX = pageWidth - writtenFolioWidth - 40
                writtenFolioPositionY = pageHeight - 40 - 12.5f
            }
        }

        writeLine(contentStream, font, firstLineText, firstLinePositionX, firstLinePositionY, pageRotation)
        writeLine(contentStream, font, writtenFolio, writtenFolioPositionX, writtenFolioPositionY, pageRotation)

        contentStream.close()
    }

    private fun calculateTextWidth(text: String, font: PDFont): Float {
        return (font.getStringWidth(text) / 1000) * fontSize
    }

    private fun writeLine(contentStream: PDPageContentStream, font: PDFont, text: String, positionX: Float, positionY: Float, pageRotation: Int) {
        contentStream.beginText()
        val matrix = Matrix.getRotateInstance(Math.toRadians(pageRotation.toDouble()), positionX, positionY)
        contentStream.setTextMatrix(matrix)
        contentStream.setFont(font, fontSize.toFloat())
        contentStream.showText(text)
        contentStream.endText()
    }
}